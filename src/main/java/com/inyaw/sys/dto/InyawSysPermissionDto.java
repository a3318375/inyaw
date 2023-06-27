package com.inyaw.sys.dto;

import com.inyaw.sys.bean.InyawSysPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class InyawSysPermissionDto extends InyawSysPermission {

    private List<InyawSysPermissionDto> children;

}
