package com.inyaw.sys.bean;

import com.mybatisflex.annotation.Table;
import lombok.Data;

@Table("sys_role")
@Data
public class SysRole {

    private Long id;

    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色代码
     */
    private String code;
    /**
     * 是否启用
     */
    private Boolean enable;
    /**
     * 备注
     */
    private String remark;
}
