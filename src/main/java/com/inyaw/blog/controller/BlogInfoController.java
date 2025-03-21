package com.inyaw.blog.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.blog.bean.BlogInfo;
import com.inyaw.blog.dto.BlogInfoDto;
import com.inyaw.blog.service.BlogInfoService;
import com.mybatisflex.core.paginate.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
@Slf4j
public class BlogInfoController {

    private final BlogInfoService blogInfoService;

    @PostMapping("/save")
    public BaseResult<String> save(@RequestBody BlogInfoDto blog) {
        blogInfoService.save(blog);
        return BaseResult.success();
    }

    @GetMapping("/list")
    public BaseResult<List<BlogInfo>> list(BlogInfo req) {
        return BaseResult.success(blogInfoService.findAll(req));
    }

    @GetMapping("/list/page")
    public BaseResult<Page<BlogInfo>> list(BlogInfoDto req) {
        return BaseResult.success(blogInfoService.findListPage(req));
    }

    @PostMapping("/delete")
    public BaseResult<String> delete(@RequestBody BlogInfo blog) {
        blogInfoService.delete(blog);
        return BaseResult.success();
    }

    @GetMapping("/info")
    public BaseResult<BlogInfo> info(BlogInfo blog) {
        return BaseResult.success(blogInfoService.getBlogInfo(blog, false));
    }

}
