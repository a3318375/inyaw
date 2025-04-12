package com.inyaw.blog.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.blog.bean.BlogInfo;
import com.inyaw.blog.dto.BlogInfoDto;
import com.inyaw.blog.service.BlogInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
@Slf4j
public class BlogInfoController {

    private final BlogInfoService blogInfoService;

    @PostMapping("/save")
    public BaseResult save(@RequestBody BlogInfoDto blog) {
        blogInfoService.save(blog);
        return BaseResult.success();
    }

    @GetMapping("/list")
    public BaseResult list(BlogInfo req) {
        return BaseResult.success(blogInfoService.findAll(req));
    }

    @GetMapping("/page")
    public BaseResult list(BlogInfoDto req) {
        return BaseResult.success(blogInfoService.findListPage(req));
    }

    @PostMapping("/delete")
    public BaseResult delete(@RequestBody BlogInfo blog) {
        blogInfoService.delete(blog);
        return BaseResult.success();
    }

    @GetMapping("/info")
    public BaseResult info(BlogInfo blog) {
        return BaseResult.success(blogInfoService.getBlogInfo(blog, false));
    }

}
