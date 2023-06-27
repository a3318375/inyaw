package com.inyaw.sys.bean;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
public class InyawSysApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String url;

    /**
     * 请求类型，0-无需登陆访问，1-登陆后访问，2-根据特殊角色访问
     */
    private Integer type;

    /**
     * 当requestType为2时，根据此字段访问
     */
    private String roleId;
}
