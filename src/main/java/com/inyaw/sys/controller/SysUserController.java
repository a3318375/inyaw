package com.inyaw.sys.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.sys.bean.SysUser;
import com.inyaw.sys.dto.SysUserDto;
import com.inyaw.sys.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;

    @GetMapping("/info")
    public BaseResult<SysUser> info(Principal principal) {
        SysUser user = sysUserService.getByUsername(principal.getName());
        return BaseResult.success(user);
    }

    @GetMapping("/list")
    public BaseResult<List<SysUser>> list(SysUser user) {
        return BaseResult.success(sysUserService.findUserList());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResult<String> save(@RequestBody SysUserDto user) {
        sysUserService.save(user, true);
        return BaseResult.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResult<String> delete(@RequestBody SysUser user) {
        sysUserService.delete(user);
        return BaseResult.success();
    }
}
