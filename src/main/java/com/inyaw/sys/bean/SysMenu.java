package com.inyaw.sys.bean;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Table("sys_menu")
@Data
public class SysMenu {

    @Id(keyType = KeyType.Auto)
    private Integer id;
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
     * 是否显示
     */
    private Boolean isShow;
    /**
     * 菜单父id
     */
    private Integer parentId;
    /**
     * 菜单排序
     */
    private Integer sort;
    /**
     * 创建日期
     */
    private LocalDateTime createTime;

}
