package com.inyaw.blog.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.blog.bean.InyawBlogType;
import com.inyaw.blog.service.InyawBlogTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
@RequiredArgsConstructor
public class InyawTypeController {

    private final InyawBlogTypeService inyawBlogTypeService;

    @PostMapping("/save")
    public BaseResult<String> save(@RequestBody InyawBlogType type) {
        inyawBlogTypeService.save(type);
        return BaseResult.success();
    }

    @GetMapping("/list")
    public BaseResult<List<InyawBlogType>> list() {
        return BaseResult.success(inyawBlogTypeService.findAll());
    }

    @PostMapping("/delete")
    public BaseResult<String> delete(@RequestBody InyawBlogType type) {
        inyawBlogTypeService.delete(type);
        return BaseResult.success();
    }
}
