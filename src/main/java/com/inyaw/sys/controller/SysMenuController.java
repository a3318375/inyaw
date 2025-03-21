package com.inyaw.sys.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.sys.bean.SysMenu;
import com.inyaw.sys.service.SysMenuService;
import com.inyaw.sys.vo.SysMenuVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public BaseResult<List<SysMenu>> list(SysMenu permission) {
        return BaseResult.success(sysMenuService.findMenuList(permission));
    }

    @GetMapping("/findMenuList")
    public BaseResult<List<SysMenuVo>> findMenuList(Boolean enable) {
        return BaseResult.success(sysMenuService.findMenuList(enable));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResult<String> save(@RequestBody  SysMenu permission) {
        sysMenuService.save(permission);
        return BaseResult.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResult<String> delete(@RequestBody SysMenu permission) {
        sysMenuService.delete(permission);
        return BaseResult.success();
    }
}
