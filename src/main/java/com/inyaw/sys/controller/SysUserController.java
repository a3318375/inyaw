package com.inyaw.sys.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.config.CurrentUser;
import com.inyaw.sys.bean.SysUser;
import com.inyaw.sys.dto.SysUserDto;
import com.inyaw.sys.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys/user")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;

    @GetMapping("/info")
    public BaseResult info(@CurrentUser String username) {
        SysUser user = sysUserService.getByUsername(username);
        user.setPassword(null);
        return BaseResult.success(user);
    }

    @GetMapping("/list")
    public BaseResult list(SysUser user) {
        return BaseResult.success(sysUserService.findUserList());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResult save(@RequestBody SysUserDto user) {
        sysUserService.save(user, true);
        return BaseResult.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResult delete(@RequestBody SysUser user) {
        sysUserService.delete(user);
        return BaseResult.success();
    }
}
