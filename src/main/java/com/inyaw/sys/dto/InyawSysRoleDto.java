package com.inyaw.sys.dto;

import com.inyaw.sys.bean.InyawSysRole;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class InyawSysRoleDto extends InyawSysRole {

    private List<Integer> menuIdList;

}
