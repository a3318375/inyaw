package com.inyaw.file.bean;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
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

    @Id(keyType = KeyType.Auto)
    private Integer id;

    /**
     * 图片链接
     */
    private String url;

    /**
     * 0-封面
     */
    private Integer type;

    /**
     * 上传方式，0-本地，1-七牛，2-又拍
     */
    private Integer uploadType;

    /**
     * 上传时间
     */
    private LocalDateTime createTime;
}
