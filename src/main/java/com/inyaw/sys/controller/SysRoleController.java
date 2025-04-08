package com.inyaw.sys.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.sys.bean.SysRole;
import com.inyaw.sys.dto.SysRoleDto;
import com.inyaw.sys.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/role")
@RequiredArgsConstructor
public class SysRoleController {

    private final SysRoleService sysRoleService;

    @GetMapping("/list")
    public BaseResult list() {
        return BaseResult.success(sysRoleService.list());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResult save(@RequestBody SysRole role) {
        sysRoleService.save(role);
        return BaseResult.success();
    }

    @RequestMapping(value = "/saveByMenuIdList", method = RequestMethod.POST)
    public BaseResult saveByMenuIdList(@RequestBody SysRoleDto roleDto) {
        sysRoleService.saveByMenuIdList(roleDto);
        return BaseResult.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResult delete(@RequestBody SysRole role) {
        sysRoleService.delete(role);
        return BaseResult.success();
    }
}
