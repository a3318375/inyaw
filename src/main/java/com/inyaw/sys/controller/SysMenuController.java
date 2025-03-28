package com.inyaw.sys.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.sys.bean.SysMenu;
import com.inyaw.sys.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author: yuxh
 * @date: 2021/3/4 0:13
 */
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class SysMenuController {

    private final SysMenuService sysMenuService;

    @GetMapping("/list")
    public BaseResult list(SysMenu permission) {
        return BaseResult.success(sysMenuService.findMenuList(permission));
    }

    @GetMapping("/findMenuList")
    public BaseResult findMenuList() {
        return BaseResult.success(sysMenuService.findMenuList());
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResult save(@RequestBody  SysMenu permission) {
        sysMenuService.save(permission);
        return BaseResult.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResult delete(@RequestBody SysMenu permission) {
        sysMenuService.delete(permission);
        return BaseResult.success();
    }
}
