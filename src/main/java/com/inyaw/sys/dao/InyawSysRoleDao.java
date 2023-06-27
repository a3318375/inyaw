package com.inyaw.sys.dao;

import com.inyaw.sys.bean.InyawSysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InyawSysRoleDao extends JpaRepository<InyawSysRole, Integer> {

    @Query(value = "select role_key from inyaw_sys_role where id in (select role_id from inyaw_sys_user_role where user_id = ?1)", nativeQuery = true)
    List<String> findRoleKeyByUserId(Integer userId);

    @Query("select roleKey from InyawSysRole where id in (select roleId from InyawSysRolePermission where permissionId = ?1)")
    List<String> findRoleKeyList(Integer id);
}
