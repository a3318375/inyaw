package com.inyaw.sys.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class InyawSysUserVo {

    private Integer id;
    private String roleName;
    private String username; // 用户名
    private String password; // 密码
    private String name;// 姓名
    private String email; // 邮箱
    private LocalDateTime loginDate;// 最后登录日期
    private String loginIp;// 最后登录IP
    private String avatar; //头像
    private Integer roleId; //角色Id
    private LocalDateTime createTime; //创建日期
    private Boolean enabled; // 账号是否可用

}
