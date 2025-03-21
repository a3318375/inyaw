package com.inyaw.blog.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.blog.bean.BlogInfo;
import com.inyaw.blog.dto.BlogInfoDto;
import com.inyaw.blog.service.BlogInfoService;
import com.inyaw.blog.vo.InyawBlogWebInfoVo;
import com.inyaw.blog.vo.InyawBlogWebVo;
import com.mybatisflex.core.paginate.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blog/web")
@RequiredArgsConstructor
@Slf4j
public class BlogInfoWebController {

    private final BlogInfoService inyawBlogService;

    @GetMapping("/archive/list")
    public BaseResult<List<BlogInfo>> archive() {
        return inyawBlogService.archive();
    }

    @GetMapping("/list")
    public BaseResult<List<InyawBlogWebVo>> findBlogList(BlogInfo req) {
        req.setStatus(true);
        return BaseResult.success(inyawBlogService.findWebList(req));
    }

    @GetMapping("/page")
    public BaseResult<Page<InyawBlogWebVo>> findBlogPage(BlogInfoDto req) {
        req.setStatus(true);
        return BaseResult.success(inyawBlogService.findWebListPage(req));
    }

    @GetMapping("/info")
    public BaseResult<InyawBlogWebInfoVo> webInfo(BlogInfo blog) {
        try {
            return BaseResult.success(inyawBlogService.getWebBlogInfo(blog));
        } catch (Exception e) {
            log.error("获取博客信息异常", e);
            return BaseResult.error();
        }
    }

}
