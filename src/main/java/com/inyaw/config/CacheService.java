package com.inyaw.config;

import com.inyaw.sys.bean.SysEnmu;
import com.inyaw.sys.service.SysEnmuService;
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

    private final Map<String, Map<String, Object>> configCache = new HashMap<>();

    private final SysEnmuService sysEnmuService;

    public Map<String, Object> getConfig(String module) {
        Map<String, Object> configMap = configCache.get(module);
        if (configMap == null || configMap.isEmpty()) {
            configMap = new HashMap<>();
            List<SysEnmu> configList = sysEnmuService.findAll(module);
            for (SysEnmu bean : configList) {
                configMap.put(bean.getCode(), bean.getValue());
            }
            configCache.put(module, configMap);
        }
        return configMap;
    }
}
