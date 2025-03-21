package com.inyaw.sys.dto;

import com.inyaw.sys.bean.SysRole;
import com.inyaw.sys.bean.SysUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: Aizmr
 * @CreateTime: 2025-03-13
 * @Description:
 */
@Setter
@Getter
public class SysUserDto extends SysUser {

    private List<SysRole> roleList;

}
