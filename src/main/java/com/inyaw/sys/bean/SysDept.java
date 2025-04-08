package com.inyaw.sys.bean;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Aizmr
 * @CreateTime: 2025-04-03
 * @Description: 部门
 */
@Table("sys_dept")
@Data
public class SysDept {

    @Id(keyType = KeyType.Auto)
    private Integer id;

    /**
     * 部门名称
     */
    private String name;
    /**
     * 父节点id
     */
    private Integer parentId;
    /**
     * 是否启用
     */
    private Boolean enable;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建日期
     */
    @Column(onInsertValue = "now()")
    private LocalDateTime createTime;
}
