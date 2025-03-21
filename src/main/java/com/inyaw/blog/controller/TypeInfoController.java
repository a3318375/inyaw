package com.inyaw.blog.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.blog.bean.TypeInfo;
import com.inyaw.blog.service.TypeInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
@RequiredArgsConstructor
public class TypeInfoController {

    private final TypeInfoService typeInfoService;

    @PostMapping("/save")
    public BaseResult<String> save(@RequestBody TypeInfo type) {
        typeInfoService.save(type);
        return BaseResult.success();
    }

    @GetMapping("/list")
    public BaseResult<List<TypeInfo>> list() {
        return BaseResult.success(typeInfoService.list());
    }

    @PostMapping("/delete")
    public BaseResult<String> delete(@RequestBody TypeInfo type) {
        typeInfoService.removeById(type);
        return BaseResult.success();
    }
}
