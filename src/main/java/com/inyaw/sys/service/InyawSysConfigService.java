package com.inyaw.sys.service;

import com.inyaw.sys.bean.InyawSysConfig;
import com.inyaw.sys.dao.InyawSysConfigDao;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InyawSysConfigService {

    private final InyawSysConfigDao inyawSysConfigDao;

    public void save(Map<String, String> req) {
        for (String key : req.keySet()) {
            if ("type".equals(key)) {
                continue;
            }
            InyawSysConfig config = inyawSysConfigDao.getByConfigKey(key);
            if (config == null) {
                config = new InyawSysConfig();
            }
            config.setConfigType(Integer.parseInt(req.get("type")));
            config.setConfigKey(key);
            config.setConfigValue(req.get(key));
            inyawSysConfigDao.save(config);
        }
    }

    public List<InyawSysConfig> findAll(Integer type) {
        InyawSysConfig params = new InyawSysConfig();
        params.setConfigType(type);
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<InyawSysConfig> ex = Example.of(params, matcher);
        return inyawSysConfigDao.findAll(ex);
    }
}
