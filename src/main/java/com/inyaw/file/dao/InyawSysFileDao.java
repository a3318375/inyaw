package com.inyaw.file.dao;

import com.inyaw.file.bean.InyawSysFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author: yuxh
 * @date: 2021/3/12 1:16
 */
public interface InyawSysFileDao extends JpaRepository<InyawSysFile, Integer> {

    @Query(value = "select * FROM inyaa_sys_file where type = ?1 ORDER BY RAND() LIMIT 1", nativeQuery = true)
    InyawSysFile getRandImg(int type);
}
