package com.inyaw.sys.bean;

import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Table("sys_menu")
@Data
public class SysMenu {

    private Long id;
    /**
     * 页面名称(code)
     */
    private String name;
    /**
     * 菜单标题
     */
    private String title;
    /**
     * 菜单路径
     */
    private String path;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 菜单父id
     */
    private Long parentId;
    /**
     * 菜单排序
     */
    private Integer sort;
    /**
     * 创建日期
     */
    private LocalDateTime createTime;

}
