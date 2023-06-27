package com.inyaw.sys.vo;

import com.inyaw.sys.bean.InyawSysRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class InyawSysRoleVo extends InyawSysRole {

    private List<Integer> menuList;

}
