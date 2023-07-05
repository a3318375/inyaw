package com.inyaw.sys.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.sys.bean.InyawSysUser;
import com.inyaw.sys.service.InyawSysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class InyawSysUserController {

    private final InyawSysUserService inyawSysUserService;

    @GetMapping("/info")
    public BaseResult<InyawSysUser> info(Principal principal) {
        InyawSysUser user = inyawSysUserService.getByUsername(principal.getName());
        return BaseResult.success(user);
    }

    @GetMapping("/list")
    public BaseResult<List<InyawSysUser>> list(InyawSysUser user) {
        return BaseResult.success(inyawSysUserService.findUserList());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResult<String> save(@RequestBody InyawSysUser user) {
        inyawSysUserService.save(user, true);
        return BaseResult.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResult<String> delete(@RequestBody InyawSysUser user) {
        inyawSysUserService.delete(user);
        return BaseResult.success();
    }
}
