package com.inyaw.sys.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class InyawSysPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单路径
     */
    private String path;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 微信菜单图标
     */
    private String wxIcon;
    /**
     * 是否显示
     */
    private Boolean isShow;
    /**
     * 菜单类型，0-目录，1-菜单，2-按钮
     */
    private Integer type;
    /**
     * 菜单父id
     */
    private Integer parentId;
    /**
     * 是否跳转外部链接
     */
    private Boolean isExt;
    /**
     * 菜单排序
     */
    private Integer sort;
    /**
     * 创建日期
     */
    private LocalDateTime createTime;
    /**
     * 更新日期
     */
    private LocalDateTime updateTime;

}
