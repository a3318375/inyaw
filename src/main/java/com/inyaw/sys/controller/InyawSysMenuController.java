package com.inyaw.sys.controller;

import com.inyaw.base.BaseResult;
import com.inyaw.sys.bean.InyawSysMenu;
import com.inyaw.sys.service.InyawSysMenuService;
import com.inyaw.sys.vo.InyawSysMenuVo;
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
public class InyawSysMenuController {

    private final InyawSysMenuService inyawSysMenuService;

    @GetMapping("/list")
    public BaseResult<List<InyawSysMenu>> list(InyawSysMenu permission) {
        return BaseResult.success(inyawSysMenuService.findMenuList(permission));
    }

    @GetMapping("/findMenuList")
    public BaseResult<List<InyawSysMenuVo>> findMenuList(Boolean enable) {
        return BaseResult.success(inyawSysMenuService.findMenuList(enable));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResult<String> save(@RequestBody InyawSysMenu permission) {
        inyawSysMenuService.save(permission);
        return BaseResult.success();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResult<String> delete(@RequestBody InyawSysMenu permission) {
        inyawSysMenuService.delete(permission);
        return BaseResult.success();
    }
}
