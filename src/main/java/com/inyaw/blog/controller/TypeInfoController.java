package com.inyaw.blog.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.blog.bean.TypeInfo;
import com.inyaw.blog.service.TypeInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/type")
@RequiredArgsConstructor
public class TypeInfoController {

    private final TypeInfoService typeInfoService;

    @PostMapping("/save")
    public BaseResult save(@RequestBody TypeInfo type) {
        typeInfoService.save(type);
        return BaseResult.success();
    }

    @GetMapping("/list")
    public BaseResult list() {
        return BaseResult.success(typeInfoService.list());
    }

    @PostMapping("/delete")
    public BaseResult delete(@RequestBody TypeInfo type) {
        typeInfoService.removeById(type);
        return BaseResult.success();
    }
}
