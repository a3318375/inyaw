package com.inyaw.blog.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.blog.bean.BlogInfo;
import com.inyaw.blog.service.BlogInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog/web")
@RequiredArgsConstructor
@Slf4j
public class BlogInfoWebController {

    private final BlogInfoService inyawBlogService;

    @GetMapping("/archive/list")
    public BaseResult archive() {
        return inyawBlogService.archive();
    }

    @GetMapping("/list")
    public BaseResult findBlogList(BlogInfo req) {
        req.setStatus(true);
        return BaseResult.success(inyawBlogService.findList(req));
    }

    @GetMapping("/page")
    public BaseResult findBlogPage(BlogInfo req) {
        req.setStatus(true);
        return BaseResult.success(inyawBlogService.findListPage(req));
    }

    @GetMapping("/info")
    public BaseResult webInfo(BlogInfo blog) {
        try {
            return BaseResult.success(inyawBlogService.getWebBlogInfo(blog));
        } catch (Exception e) {
            log.error("获取博客信息异常", e);
            return BaseResult.error();
        }
    }

}
