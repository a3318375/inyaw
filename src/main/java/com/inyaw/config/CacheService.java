package com.inyaw.config;

import com.inyaw.sys.bean.SysConfig;
import com.inyaw.sys.service.SysConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存部分，配置文件缓存
 *
 * @author: yuxh
 * @date: 2021/4/5 23:44
 */
@Service
@RequiredArgsConstructor
public class CacheService {

    private final Map<Integer, Map<String, Object>> configCache = new HashMap<>();

    private final SysConfigService sysConfigService;

    public Map<String, Object> getConfig(Integer type) {
        Map<String, Object> configMap = configCache.get(type);
        if (configMap == null || configMap.isEmpty()) {
            configMap = new HashMap<>();
            List<SysConfig> configList = sysConfigService.findAll(type);
            for (SysConfig bean : configList) {
                configMap.put(bean.getConfigKey(), bean.getConfigValue());
            }
            configCache.put(type, configMap);
        }
        return configMap;
    }
}
