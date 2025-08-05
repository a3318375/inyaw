package com.inyaw.file.bean;

import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: yuxh
 * @date: 2021/3/29 23:14
 */
@Table("sys_file")
@Data
public class SysFile {

    private Long id;

    /**
     * 文件链接
     */
    private String url;

    /**
     * 文件类型
     */
    private Integer type;

    /**
     * 上传方式，0-本地，1-七牛，2-又拍
     */
    private Integer ossType;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
