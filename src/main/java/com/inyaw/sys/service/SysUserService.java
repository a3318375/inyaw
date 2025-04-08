package com.inyaw.sys.service;

import com.inyaw.sys.bean.SysUser;
import com.inyaw.sys.dao.SysRoleMapper;
import com.inyaw.sys.dao.SysUserMapper;
import com.inyaw.sys.dto.SysUserDto;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.inyaw.sys.bean.table.SysRoleTableDef.SYS_ROLE;
import static com.inyaw.sys.bean.table.SysUserRoleTableDef.SYS_USER_ROLE;
import static com.inyaw.sys.bean.table.SysUserTableDef.SYS_USER;
import static com.mybatisflex.core.query.QueryMethods.select;

@Service
public class SysUserService implements UserDetailsService {

    @Resource
    private final SysUserMapper sysUserMapper;
    private final SysRoleMapper sysRoleMapper;

    public SysUserService(SysUserMapper sysUserMapper, SysRoleMapper sysRoleMapper) {
        this.sysUserMapper = sysUserMapper;
        this.sysRoleMapper = sysRoleMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            QueryWrapper queryWrapper = QueryWrapper.create().select(SYS_ROLE.CODE)
                    .from(SYS_ROLE).where(SYS_ROLE.ID.in(select(SYS_USER_ROLE.ROLE_ID)
                            .from(SYS_USER_ROLE).leftJoin(SYS_USER).on(SYS_USER_ROLE.USER_ID.eq(SYS_USER.ID))
                            .where(SYS_USER.ID.eq(user.getId()))));
            List<String> roles = sysRoleMapper.selectObjectListByQueryAs(queryWrapper, String.class);
            return buildUserForAuthentication(user, buildUserAuthority(roles));
        }
    }


    private User buildUserForAuthentication(SysUser user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), user.getEnabled(), user.getAccountNonExpired(),
                user.getCredentialsNonExpired(), user.getAccountNonLocked(), authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(List<String> userRoles) {
        Set<GrantedAuthority> setAuths = new HashSet<>();

        for (String userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole));
        }
        return new ArrayList<>(setAuths);
    }

    public List<SysUser> findAll(SysUser user) {
        QueryWrapper queryWrapper = QueryWrapper.create(user);
        return sysUserMapper.selectListByQuery(queryWrapper);
    }

    public List<SysUser> findUserList() {
        return sysUserMapper.selectAll();
    }

    public void delete(SysUser user) {
        sysUserMapper.delete(user);
    }

    public void save(SysUserDto userDto, boolean updatePassword) {
        if (StringUtils.isNotBlank(userDto.getPassword()) && updatePassword) {
            BCryptPasswordEncoder bcry = new BCryptPasswordEncoder();
            userDto.setPassword(bcry.encode(userDto.getPassword()));
        }
        if (userDto.getAccountNonLocked() == null) {
            userDto.setAccountNonLocked(true);
        }
        if (userDto.getAccountNonExpired() == null) {
            userDto.setAccountNonExpired(true);
        }
        if (userDto.getCredentialsNonExpired() == null) {
            userDto.setCredentialsNonExpired(true);
        }
        if (userDto.getRoleList() != null && !userDto.getRoleList().isEmpty()) {
            sysRoleMapper.insertBatch(userDto.getRoleList());
        }
        sysUserMapper.insert(userDto);
    }

    public SysUser getByUsername(String username) {
        QueryWrapper userParams = QueryWrapper.create();
        userParams.eq("username", username);
        return sysUserMapper.selectOneByQuery(userParams);
    }
}
