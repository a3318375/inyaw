package com.inyaw.sys.dto;

import com.inyaw.sys.bean.SysRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SysRoleDto extends SysRole {

    private List<Integer> menuIdList;

}
