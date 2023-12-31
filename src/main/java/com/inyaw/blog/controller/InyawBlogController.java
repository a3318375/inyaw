package com.inyaw.blog.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.blog.bean.InyawBlog;
import com.inyaw.blog.dto.InyawBlogDto;
import com.inyaw.blog.service.InyawBlogService;
import com.inyaw.blog.vo.InyawBlogVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/blog")
@RequiredArgsConstructor
@Slf4j
public class InyawBlogController {

    private final InyawBlogService inyawBlogService;

    @PostMapping("/save")
    public BaseResult<String> save(@RequestBody InyawBlogDto blog, Principal principal) {
        inyawBlogService.save(blog, principal);
        return BaseResult.success();
    }

    @GetMapping("/list")
    public BaseResult<List<InyawBlog>> list(InyawBlog req) {
        return BaseResult.success(inyawBlogService.findAll(req));
    }

    @GetMapping("/list/page")
    public BaseResult<Page<InyawBlogVo>> list(InyawBlogDto req) {
        return BaseResult.success(inyawBlogService.findAdminListPage(req));
    }

    @PostMapping("/delete")
    public BaseResult<String> delete(@RequestBody InyawBlog blog) {
        inyawBlogService.delete(blog);
        return BaseResult.success();
    }

    @GetMapping("/info")
    public BaseResult<InyawBlog> info(InyawBlog blog) {
        return BaseResult.success(inyawBlogService.getBlogInfo(blog, false));
    }

}
