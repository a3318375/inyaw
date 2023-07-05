package com.inyaw.sys.dao;


import com.inyaw.sys.bean.InyawSysConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InyawSysConfigDao extends JpaRepository<InyawSysConfig, Integer> {
    InyawSysConfig getByConfigKey(String key);
}
