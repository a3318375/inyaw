package com.inyaw.sys.dao;


import com.inyaw.sys.bean.SysConfig;
import com.mybatisflex.core.BaseMapper;

public interface SysConfigMapper extends BaseMapper<SysConfig> {
    SysConfig getByConfigKey(String key);
}
