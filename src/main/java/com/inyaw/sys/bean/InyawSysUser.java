package com.inyaw.sys.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
public class InyawSysUser implements Serializable {

    private static final long serialVersionUID = -4307367511896826563L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 账号是否未过期
     */
    private Boolean accountNonExpired;
    /**
     * 账号是否未锁定
     */
    private Boolean accountNonLocked;
    /**
     * 账号是否可用
     */
    private Boolean enabled;
    /**
     * 账号凭证是否未过期
     */
    private Boolean credentialsNonExpired;
    /**
     * 用户详情资料
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id",
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private InyawSysUserDetail inyawSysUserDetail;
    /**
     * 角色
     */
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private List<InyawSysRole> roleList;
}
