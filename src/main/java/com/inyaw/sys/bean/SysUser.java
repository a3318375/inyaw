package com.inyaw.sys.bean;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Table("sys_user")
@Data
public class SysUser {

    @Id(keyType = KeyType.Auto)
    private Integer id;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录日期
     */
    private LocalDateTime loginDate;
    /**
     * 账号是否可用
     */
    private Boolean enabled;
    /**
     * 账号是否未过期
     */
    private Boolean accountNonExpired;
    /**
     * 账号是否未锁定
     */
    private Boolean accountNonLocked;

    /**
     * 账号凭证是否未过期
     */
    private Boolean credentialsNonExpired;
}
