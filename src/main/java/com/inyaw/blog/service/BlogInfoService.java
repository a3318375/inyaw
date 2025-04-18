package com.inyaw.blog.service;

import com.inyaw.base.BaseResult;
import com.inyaw.blog.bean.BlogArticle;
import com.inyaw.blog.bean.BlogInfo;
import com.inyaw.blog.bean.BlogTag;
import com.inyaw.blog.bean.TypeInfo;
import com.inyaw.blog.dao.BlogArticleMapper;
import com.inyaw.blog.dao.BlogInfoMapper;
import com.inyaw.blog.dao.BlogTagMapper;
import com.inyaw.blog.dao.TypeInfoMapper;
import com.inyaw.blog.dto.BlogInfoDto;
import com.inyaw.blog.vo.InyawBlogInfoVo;
import com.inyaw.blog.vo.InyawBlogVo;
import com.inyaw.blog.vo.InyawBlogWebInfoVo;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.inyaw.blog.bean.table.BlogInfoTableDef.BLOG_INFO;
import static com.inyaw.blog.bean.table.TypeInfoTableDef.TYPE_INFO;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogInfoService {

    private final BlogInfoMapper blogInfoMapper;
    private final BlogArticleMapper blogArticleMapper;
    private final BlogTagMapper blogTagMapper;
    private final TypeInfoMapper typeInfoMapper;

    public List<BlogInfo> findAll(BlogInfo req) {
        QueryWrapper queryWrapper = QueryWrapper.create().orderBy(BLOG_INFO.CREATE_TIME.desc()).where(BLOG_INFO.TITLE.eq(req.getTitle()));
        return blogInfoMapper.selectListByQuery(queryWrapper);
    }

    public List<InyawBlogVo> findList(BlogInfo req) {
        QueryWrapper queryWrapper = QueryWrapper.create(req)
                .select(BLOG_INFO.ALL_COLUMNS, TYPE_INFO.NAME.as("typeName"))
                .from(BLOG_INFO)
                .leftJoin(TYPE_INFO).on(BLOG_INFO.TYPE_ID.eq(TYPE_INFO.ID))
                .orderBy(BLOG_INFO.CREATE_TIME.desc());
        return blogInfoMapper.selectListByQueryAs(queryWrapper, InyawBlogVo.class);
    }

    public Page<InyawBlogVo> findListPage(BlogInfo req) {
        QueryWrapper queryWrapper = QueryWrapper.create(req)
                .select(BLOG_INFO.ALL_COLUMNS, TYPE_INFO.NAME.as("typeName"))
                .from(BLOG_INFO)
                .leftJoin(TYPE_INFO).on(BLOG_INFO.TYPE_ID.eq(TYPE_INFO.ID))
                .orderBy(BLOG_INFO.CREATE_TIME.desc());
        return blogInfoMapper.paginateAs(req.getPageNum(), req.getPageSize(), queryWrapper, InyawBlogVo.class);
    }

    @Transactional
    public void save(BlogInfoDto req) {
        BlogInfo blog = new BlogInfo();
        BeanUtils.copyProperties(req, blog);
        if (req.getId() != null) {
            blog.setUpdateTime(LocalDateTime.now());
        } else {
            blog.setCreateTime(LocalDateTime.now());
            blog.setViews(0);
        }
        blogInfoMapper.insert(blog);
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

    public BlogInfo getBlogInfo(BlogInfo req, boolean isUpdate) {
        BlogInfo blog = blogInfoMapper.selectOneById(req.getId());
        if (isUpdate) {
            blog.setViews(blog.getViews() + 1);
            blogInfoMapper.update(blog);
        }
        return blog;
    }

    public InyawBlogInfoVo getBlogInfo(BlogInfo req) {
        InyawBlogInfoVo vo = new InyawBlogInfoVo();
        BlogInfo bean = getBlogInfo(req, false);
        BeanUtils.copyProperties(bean, vo);
        BlogArticle blogArticle = blogArticleMapper.selectOneById(bean.getId());
        if (blogArticle != null) {
            vo.setContext(blogArticle.getContext());
        }
        return vo;
    }

    public InyawBlogWebInfoVo getWebBlogInfo(BlogInfo req) {
        InyawBlogWebInfoVo vo = new InyawBlogWebInfoVo();
        BlogInfo bean = getBlogInfo(req, true);
        BeanUtils.copyProperties(bean, vo);
        BlogArticle blogArticle = blogArticleMapper.selectOneById(bean.getId());
        if (blogArticle != null) {
            vo.setContext(blogArticle.getContext());
        }
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
