package com.inyaw.sys.dao;


import com.inyaw.sys.bean.SysEnmu;
import com.mybatisflex.core.BaseMapper;

public interface SysEnmuMapper extends BaseMapper<SysEnmu> {

    SysEnmu getByCode(String code);

}
