package com.inyaw.sys.dao;


import com.inyaw.sys.bean.InyawSysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InyawSysUserDao extends JpaRepository<InyawSysUser, Integer> {

    InyawSysUser getByUsername(String username);

}
