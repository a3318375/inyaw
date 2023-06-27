package com.inyaw.sys.dao;


import com.inyaw.sys.bean.InyawSysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InyawSysPermissionDao extends JpaRepository<InyawSysPermission, Integer> {

    public List<InyawSysPermission> findByTypeNotAndIsShowIsAndParentIdIsOrderBySortAsc(int type, boolean isShow, int parentId);

    @Query("select u from InyawSysPermission u where u.type <> 2 and u.isShow = true and u.parentId = ?1 and u.id in (select permissionId from InyawSysRolePermission where roleId = ?2) order by u.sort")
    public List<InyawSysPermission> findMenuList(Integer parentId, Integer roleId);

    @Query("select path from InyawSysPermission where type = 2 and isShow = ?1 and id in (select permissionId from InyawSysRolePermission where roleId = ?2) order by sort")
    public List<String> findButtonList(boolean isShow, Integer roleId);

    @Query("select u.path from InyawSysPermission u where u.type = 1 and u.isShow = true and u.id in (select permissionId from InyawSysRolePermission where roleId = ?1) order by u.sort")
    public List<String> findMenuPathList(Integer roleId);
}
