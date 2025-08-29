package com.inyaw.blog.service;

import com.inyaw.base.BaseResult;
import com.inyaw.blog.bean.BlogInfo;
import com.inyaw.blog.bean.BlogTag;
import com.inyaw.blog.bean.TypeInfo;
import com.inyaw.blog.dao.BlogInfoMapper;
import com.inyaw.blog.dao.BlogTagMapper;
import com.inyaw.blog.dao.TypeInfoMapper;
import com.inyaw.blog.dto.BlogInfoDto;
import com.inyaw.blog.vo.InyawBlogVo;
import com.inyaw.blog.vo.InyawBlogWebInfoVo;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.inyaw.blog.bean.table.BlogInfoTableDef.BLOG_INFO;
import static com.inyaw.blog.bean.table.TypeInfoTableDef.TYPE_INFO;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogInfoService {

    private final BlogInfoMapper blogInfoMapper;
    private final BlogTagMapper blogTagMapper;
    private final TypeInfoMapper typeInfoMapper;

    public List<BlogInfo> findAll(BlogInfo req) {
        QueryWrapper queryWrapper = QueryWrapper.create().select(BLOG_INFO.DEFAULT_COLUMNS).orderBy(BLOG_INFO.CREATE_TIME.desc()).where(BLOG_INFO.TITLE.eq(req.getTitle()));
        return blogInfoMapper.selectListByQuery(queryWrapper);
    }

    public List<InyawBlogVo> findList(BlogInfo req) {
        QueryWrapper queryWrapper = QueryWrapper.create(req)
                .select(TYPE_INFO.NAME.as("typeName"))
                .from(BLOG_INFO)
                .leftJoin(TYPE_INFO).on(BLOG_INFO.TYPE_ID.eq(TYPE_INFO.ID))
                .orderBy(BLOG_INFO.CREATE_TIME.desc());
        return blogInfoMapper.selectListByQueryAs(queryWrapper, InyawBlogVo.class);
    }

    public Page<InyawBlogVo> findListPage(BlogInfo req) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select(BLOG_INFO.DEFAULT_COLUMNS)
                .select(TYPE_INFO.NAME.as("typeName"))
                .from(BLOG_INFO)
                .leftJoin(TYPE_INFO).on(BLOG_INFO.TYPE_ID.eq(TYPE_INFO.ID))
                .orderBy(BLOG_INFO.CREATE_TIME.desc());
        return blogInfoMapper.paginateAs(req.getPageNumber(), req.getPageSize(), queryWrapper, InyawBlogVo.class);
    }

    @Transactional
    public void save(BlogInfoDto req) {
        BlogInfo blog = new BlogInfo();
        BeanUtils.copyProperties(req, blog);
        blogInfoMapper.insert(req);
        if (req.getTagList() != null && !req.getTagList().isEmpty()) {
            List<BlogTag> blogTagList = new ArrayList<>();
            req.getTagList().forEach(tagInfo -> {
                BlogTag blogTag = new BlogTag();
                blogTag.setTagId(blog.getId());
                blogTag.setBlogId(tagInfo.getId());
                blogTagList.add(blogTag);
            });
            blogTagMapper.insertBatch(blogTagList);
        }
    }

    public void delete(BlogInfo req) {
        blogInfoMapper.deleteById(req.getId());
    }

    public BlogInfo getBlogInfo(BlogInfo req) {
        return blogInfoMapper.selectOneById(req.getId());
    }

    public InyawBlogWebInfoVo getWebBlogInfo(BlogInfo req) {
        InyawBlogWebInfoVo vo = new InyawBlogWebInfoVo();
        BlogInfo bean = blogInfoMapper.selectOneById(req.getId());
        BeanUtils.copyProperties(bean, vo);
        if (bean.getTypeId() != null) {
            TypeInfo type = typeInfoMapper.selectOneById(bean.getTypeId());
            if (type != null) {
                vo.setTypeName(type.getName());
            }
        }

        QueryWrapper lastQuery = QueryWrapper.create().orderBy(BLOG_INFO.ID.desc()).where(BLOG_INFO.ID.lt(vo.getId())).limit(1);
        BlogInfo last = blogInfoMapper.selectOneByQuery(lastQuery);
        if (last != null) {
            InyawBlogVo lastVo = new InyawBlogVo();
            BeanUtils.copyProperties(last, lastVo);
            vo.setPreviousBlog(lastVo);
        }

        QueryWrapper nextQuery = QueryWrapper.create().orderBy(BLOG_INFO.ID.asc()).where(BLOG_INFO.ID.gt(vo.getId())).limit(1);
        BlogInfo next = blogInfoMapper.selectOneByQuery(nextQuery);
        if (next != null) {
            InyawBlogVo nextVo = new InyawBlogVo();
            BeanUtils.copyProperties(next, nextVo);
            vo.setNextBlog(nextVo);
        }
        return vo;
    }

    public BaseResult archive() {
        BlogInfo params = new BlogInfo();
        params.setStatus(true);
        List<BlogInfo> list = findAll(params);
        return BaseResult.success(list);
    }
}
