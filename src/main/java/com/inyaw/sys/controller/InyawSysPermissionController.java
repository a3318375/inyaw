package com.inyaw.sys.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.sys.bean.InyawSysPermission;
import com.inyaw.sys.bean.InyawSysUser;
import com.inyaw.sys.dto.InyawSysPermissionDto;
import com.inyaw.sys.service.InyawSysPermissionService;
import com.inyaw.sys.service.InyawSysUserService;
import com.inyaw.sys.vo.InyawSysPermissionVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @author: yuxh
 * @date: 2021/3/4 0:13
 */
@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
public class InyawSysPermissionController {

    private final InyawSysPermissionService inyawSysPermissionService;
    private final InyawSysUserService inyawSysUserService;

    @GetMapping("/list")
    public BaseResult<List<InyawSysPermission>> list(InyawSysPermission permission) {
        return BaseResult.success(inyawSysPermissionService.findMenuList(permission));
    }

    @GetMapping("/findMenuList")
    public BaseResult<List<InyawSysPermissionDto>> findMenuList(Principal principal, Integer isWx) {
        InyawSysUser user = inyawSysUserService.getByUsername(principal.getName());
        return BaseResult.success(inyawSysPermissionService.findMenuList(user.getInyawSysRole().getId(), isWx));
    }

    @GetMapping("/findMenuPathList")
    public BaseResult<List<String>> findMenuPathList(Principal principal) {
        InyawSysUser user = inyawSysUserService.getByUsername(principal.getName());
        return BaseResult.success(inyawSysPermissionService.findMenuPathList(user.getInyawSysRole().getId()));
    }

    @GetMapping("/findButtonList")
    public BaseResult<List<String>> findButtonList(Principal principal) {
        InyawSysUser user = inyawSysUserService.getByUsername(principal.getName());
        return BaseResult.success(inyawSysPermissionService.findButtonList(user.getInyawSysRole().getId()));
    }

    @GetMapping("/findPermissionList")
    public BaseResult<List<InyawSysPermissionVo>> findPermissionList(Boolean enable) {
        return BaseResult.success(inyawSysPermissionService.findPermissionList(enable));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResult<String> save(@RequestBody InyawSysPermission permission) {
        inyawSysPermissionService.save(permission);
        return BaseResult.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResult<String> delete(@RequestBody InyawSysPermission permission) {
        inyawSysPermissionService.delete(permission);
        return BaseResult.success();
    }
}
