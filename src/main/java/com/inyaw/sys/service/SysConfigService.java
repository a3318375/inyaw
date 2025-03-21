package com.inyaw.sys.service;

import com.inyaw.sys.bean.SysConfig;
import com.inyaw.sys.dao.SysConfigMapper;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SysConfigService {

    private final SysConfigMapper sysConfigMapper;

    public void save(Map<String, String> req) {
        for (String key : req.keySet()) {
            if ("type".equals(key)) {
                continue;
            }
           SysConfig config = sysConfigMapper.getByConfigKey(key);
            if (config == null) {
                config = new SysConfig();
            }
            config.setConfigType(Integer.parseInt(req.get("type")));
            config.setConfigKey(key);
            config.setConfigValue(req.get(key));
            sysConfigMapper.insert(config);
        }
    }

    public List<SysConfig> findAll(Integer type) {
        SysConfig params = new SysConfig();
        params.setConfigType(type);
        QueryWrapper queryWrapper = QueryWrapper.create(params);
        return sysConfigMapper.selectListByQuery(queryWrapper);
    }
}
