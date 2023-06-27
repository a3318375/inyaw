package com.inyaw.file.bean;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author: yuxh
 * @date: 2021/3/29 23:14
 */
@Setter
@Getter
@Entity
public class InyawSysFile {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键生成策略
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
