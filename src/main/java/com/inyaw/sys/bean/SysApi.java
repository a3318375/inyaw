package com.inyaw.sys.bean;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;


@Table("sys_api")
@Data
public class SysApi {

    @Id(keyType = KeyType.Auto)
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
