package com.inyaw.sys.vo;

import com.inyaw.sys.bean.SysMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SysMenuVo extends SysMenu {

    private List<SysMenuVo> children;

}
