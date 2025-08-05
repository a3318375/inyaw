package com.inyaw.blog.bean;

import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Table("type_info")
@Data
public class TypeInfo {

    private Long id;

    /**
     * 分类名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
