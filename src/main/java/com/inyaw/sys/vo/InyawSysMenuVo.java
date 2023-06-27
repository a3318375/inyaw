package com.inyaw.sys.vo;

import com.inyaw.sys.bean.InyawSysMenu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class InyawSysMenuVo extends InyawSysMenu {

    private List<InyawSysMenuVo> children;

}
