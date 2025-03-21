package com.inyaw.sys.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.sys.bean.SysRole;
import com.inyaw.sys.dto.SysRoleDto;
import com.inyaw.sys.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class SysRoleController {

    private final SysRoleService sysRoleService;

    @GetMapping("/list")
    public BaseResult<List<SysRole>> list() {
        return BaseResult.success(sysRoleService.list());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResult<String> save(@RequestBody SysRole role) {
        sysRoleService.save(role);
        return BaseResult.success();
    }

    @RequestMapping(value = "/saveByMenuIdList", method = RequestMethod.POST)
    public BaseResult<String> saveByMenuIdList(@RequestBody SysRoleDto roleDto) {
        sysRoleService.saveByMenuIdList(roleDto);
        return BaseResult.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResult<String> delete(@RequestBody SysRole role) {
        sysRoleService.delete(role);
        return BaseResult.success();
    }
}
