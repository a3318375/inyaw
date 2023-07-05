package com.inyaw.sys.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.sys.bean.InyawSysRole;
import com.inyaw.sys.service.InyawSysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class InyawSysRoleController {

    private final InyawSysRoleService inyawSysRoleService;

    @GetMapping("/list")
    public BaseResult<List<InyawSysRole>> list() {
        return BaseResult.success(inyawSysRoleService.list());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResult<String> save(@RequestBody InyawSysRole role) {
        inyawSysRoleService.save(role);
        return BaseResult.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResult<String> delete(@RequestBody InyawSysRole role) {
        inyawSysRoleService.delete(role);
        return BaseResult.success();
    }
}
