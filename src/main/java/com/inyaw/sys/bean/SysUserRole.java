package com.inyaw.sys.bean;

import com.mybatisflex.annotation.Table;
import lombok.Data;

@Table("sys_user_role")
@Data
public class SysUserRole {

    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;
}
