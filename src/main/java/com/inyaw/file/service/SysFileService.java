package com.inyaw.file.service;

import com.inyaw.file.bean.SysFile;
import com.inyaw.file.dao.SysFileMapper;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: yuxh
 * @date: 2021/3/29 23:20
 */
@Service
@RequiredArgsConstructor
public class SysFileService {

    private final SysFileMapper sysFileMapper;

    public void save(String url, int uploadType, Integer type) {
        SysFile sysFile = new SysFile();
        sysFile.setUrl(url);
        sysFile.setType(type == null ? 0 : type);
        sysFile.setUploadType(uploadType);
        sysFile.setCreateTime(LocalDateTime.now());
        sysFileMapper.insert(sysFile);
    }

    public List<SysFile> findAll() {
        return sysFileMapper.selectAll();
    }

    public SysFile getRandImg(int type) {
        QueryWrapper queryWrapper = QueryWrapper.create();
        queryWrapper.eq("type", type);
        queryWrapper.orderBy("rand()").limit(1);
        // @Query(value = "select * FROM inyaw_sys_file where type = ?1 ORDER BY RAND() LIMIT 1", nativeQuery = true)
        return sysFileMapper.selectOneByQuery(queryWrapper);
    }

    public SysFile getOne(Integer id) {
        return sysFileMapper.selectOneById(id);
    }
}
