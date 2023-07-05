package com.inyaw.sys.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Accessors(chain = true)
public class InyawSysDept {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 上级部门
     */
    private Integer parentId;

    /**
     * 启用/停用
     */
    private Boolean status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Integer sort;
}
