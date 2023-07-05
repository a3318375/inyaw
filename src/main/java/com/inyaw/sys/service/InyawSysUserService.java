package com.inyaw.sys.service;

import com.inyaw.sys.bean.InyawSysUser;
import com.inyaw.sys.bean.InyawSysUserDetail;
import com.inyaw.sys.dao.InyawSysRoleDao;
import com.inyaw.sys.dao.InyawSysUserDao;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InyawSysUserService implements UserDetailsService {

    @Resource
    private InyawSysUserDao inyawSysUserDao;
    private final InyawSysRoleDao inyawSysRoleDao;

    public InyawSysUserService(InyawSysRoleDao inyawSysRoleDao) {
        this.inyawSysRoleDao = inyawSysRoleDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        InyawSysUser user = inyawSysUserDao.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            List<String> roles = new ArrayList<>();
            user.getRoleList().forEach(roleBean -> {
                roles.add(roleBean.getRoleKey());
            });
            //List<String> roles = new ArrayList<>();
            return buildUserForAuthentication(user, buildUserAuthority(roles));
        }
    }

    public InyawSysUser getByUsername(String username) {
        return inyawSysUserDao.getByUsername(username);
    }

    private User buildUserForAuthentication(InyawSysUser user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), user.getEnabled(), user.getAccountNonExpired(), user.getCredentialsNonExpired(), user.getAccountNonLocked(), authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(List<String> userRoles) {
        Set<GrantedAuthority> setAuths = new HashSet<>();

        for (String userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole));
        }
        return new ArrayList<>(setAuths);
    }

    public List<InyawSysUser> findAll(InyawSysUser user) {
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<InyawSysUser> ex = Example.of(user, matcher);
        return inyawSysUserDao.findAll(ex);
    }

    public List<InyawSysUser> findUserList() {
        return inyawSysUserDao.findAll();
    }

    public void delete(InyawSysUser user) {
        inyawSysUserDao.delete(user);
    }

    public void save(InyawSysUser user, boolean updatePassword) {
        if (StringUtils.isNotBlank(user.getPassword()) && updatePassword) {
            BCryptPasswordEncoder bcry = new BCryptPasswordEncoder();
            user.setPassword(bcry.encode(user.getPassword()));
        }
        if (user.getInyawSysUserDetail() == null) {
            user.setInyawSysUserDetail(new InyawSysUserDetail());
        }
        if (user.getInyawSysUserDetail().getCreateTime() == null) {
            user.getInyawSysUserDetail().setCreateTime(LocalDateTime.now());
        }
        if (user.getAccountNonLocked() != null) {
            user.setAccountNonLocked(true);
        }
        if (user.getAccountNonExpired() != null) {
            user.setAccountNonExpired(true);
        }
        if (user.getCredentialsNonExpired() != null) {
            user.setCredentialsNonExpired(true);
        }
        if (user.getRoleList() != null && user.getRoleList().size() > 0) {
            inyawSysRoleDao.saveAll(user.getRoleList());
        }
        inyawSysUserDao.save(user);
    }
}
