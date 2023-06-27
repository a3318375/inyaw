package com.inyaw.sys.dao;

import com.inyaw.sys.bean.InyawSysRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InyawSysRolePermissionDao extends JpaRepository<InyawSysRolePermission, Integer> {

    @Query("select permissionId from InyawSysRolePermission where roleId = ?1")
    List<Integer> findPermissionIdByRoleId(Integer roleId);

    void deleteByRoleId(Integer id);
}
