package com.inyaw.sys.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.sys.bean.SysDept;
import com.inyaw.sys.service.SysDeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dept")
@RequiredArgsConstructor
public class SysDeptController {

    private final SysDeptService sysDeptService;

    @GetMapping("/list")
    public BaseResult list() {
        return BaseResult.success(sysDeptService.list());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResult save(@RequestBody SysDept sysDept) {
        sysDeptService.save(sysDept);
        return BaseResult.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResult delete(@RequestBody SysDept sysDept) {
        sysDeptService.removeById(sysDept);
        return BaseResult.success();
    }
}
