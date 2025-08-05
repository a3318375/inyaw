package com.inyaw.sys.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Table("sys_user")
@Data
public class SysUser {

    @Id(keyType = KeyType.Auto)
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    @JsonIgnore
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
     * 账号是否可用
     */
    private Boolean enabled;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    @JsonCreator
    public SysUser(@JsonProperty("id") Long id, @JsonProperty("id") String username, @JsonProperty("id") String password,
                   @JsonProperty("id") String nickname, @JsonProperty("id") String avatar,
                   @JsonProperty("id") String email, @JsonProperty("id") Boolean enabled,
                   @JsonProperty("id") LocalDateTime createTime, @JsonProperty("id") LocalDateTime updateTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.avatar = avatar;
        this.email = email;
        this.enabled = enabled;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
