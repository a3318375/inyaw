package com.inyaw.blog.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.blog.bean.InyawBlog;
import com.inyaw.blog.bean.InyawBlogComment;
import com.inyaw.blog.dto.InyawBlogDto;
import com.inyaw.blog.service.InyawBlogCommentService;
import com.inyaw.blog.service.InyawBlogService;
import com.inyaw.blog.vo.InyawBlogWebInfoVo;
import com.inyaw.blog.vo.InyawBlogWebVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blog/web")
@RequiredArgsConstructor
@Slf4j
public class InyawBlogWebController {

    private final InyawBlogService inyawBlogService;
    private final InyawBlogCommentService inyawBlogCommentService;

    @GetMapping("/archive/list")
    public BaseResult<List<InyawBlog>> archive() {
        return inyawBlogService.archive();
    }

    @GetMapping("/list")
    public BaseResult<List<InyawBlogWebVo>> findBlogList(InyawBlog req) {
        req.setStatus(true);
        return BaseResult.success(inyawBlogService.findWebList(req));
    }

    @GetMapping("/page")
    public BaseResult<Page<InyawBlogWebVo>> findBlogPage(InyawBlogDto req) {
        req.setStatus(true);
        return BaseResult.success(inyawBlogService.findWebListPage(req));
    }
    @GetMapping("/info")
    public BaseResult<InyawBlogWebInfoVo> webInfo(InyawBlog blog) {
        try {
            return BaseResult.success(inyawBlogService.getWebBlogInfo(blog));
        } catch (Exception e) {
            log.error("获取博客信息异常", e);
            return BaseResult.error();
        }
    }

    @PostMapping("/comment/save")
    public BaseResult<String> saveBlogComment(InyawBlogComment req) {
        inyawBlogCommentService.saveBlogComment(req);
        return BaseResult.success();
    }

}
