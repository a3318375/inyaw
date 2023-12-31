package com.inyaw.file.service;

import com.inyaw.file.bean.InyawSysFile;
import com.inyaw.file.dao.InyawSysFileDao;
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
public class InyawSysFileService {

    private final InyawSysFileDao inyawSysFileDao;

    public void save(String url, int uploadType, Integer type) {
        InyawSysFile sysFile = new InyawSysFile();
        sysFile.setUrl(url);
        sysFile.setType(type == null ? 0 : type);
        sysFile.setUploadType(uploadType);
        sysFile.setCreateTime(LocalDateTime.now());
        inyawSysFileDao.save(sysFile);
    }

    public List<InyawSysFile> findAll() {
        return inyawSysFileDao.findAll();
    }

    public InyawSysFile getRandImg(int type) {
        return inyawSysFileDao.getRandImg(type);
    }

    public InyawSysFile getOne(Integer id) {
        return inyawSysFileDao.getOne(id);
    }
}
