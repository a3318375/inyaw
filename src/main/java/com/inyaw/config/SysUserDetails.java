package com.inyaw.config;

import com.inyaw.sys.bean.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Aizmr
 * @CreateTime: 2025-08-06
 * @Description:
 */
public class SysUserDetails extends SysUser implements UserDetails {

    private static List<GrantedAuthority> ROLE_USER = Collections
            .unmodifiableList(AuthorityUtils.createAuthorityList("ROLE_USER"));

    public SysUserDetails(SysUser sysUser, List<GrantedAuthority> authorities) {
        super(sysUser.getId(), sysUser.getUsername(), sysUser.getPassword(), sysUser.getNickname(),
                sysUser.getAvatar(), sysUser.getEmail(), sysUser.getEnabled(), sysUser.getCreateTime(), sysUser.getUpdateTime());
        ROLE_USER = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ROLE_USER;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
