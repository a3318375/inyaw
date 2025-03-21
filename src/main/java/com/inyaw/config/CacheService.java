package com.inyaw.config;

import com.inyaw.sys.bean.SysApi;
import com.inyaw.sys.bean.SysConfig;
import com.inyaw.sys.bean.SysRole;
import com.inyaw.sys.dao.SysApiMapper;
import com.inyaw.sys.service.SysConfigService;
import com.inyaw.sys.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 缓存部分，配置文件缓存
 *
 * @author: yuxh
 * @date: 2021/4/5 23:44
 */
@Service
@RequiredArgsConstructor
public class CacheService {

    private final Map<String, Collection<ConfigAttribute>> AuthCache = new HashMap<>();
    private final Map<Integer, Map<String, Object>> configCache = new HashMap<>();
    private final Map<String, Object> objectCache = new HashMap<>();

    private final SysApiMapper sysApiMapper;
    private final SysRoleService sysRoleService;
    private final SysConfigService sysConfigService;

    public Map<String, Collection<ConfigAttribute>> getConfigAttributeMap() {
        if (AuthCache.isEmpty()) {
            List<SysApi> list = sysApiMapper.selectAll();
            for (SysApi api : list) {
                List<ConfigAttribute> configAttributeList = new ArrayList<>();
                ConfigAttribute configAttribute;
                switch (api.getType()) {
                    case 0 -> configAttribute = new SecurityConfig("ROLE_ANY");
                    case 1 -> configAttribute = new SecurityConfig("ROLE_LOGIN");
                    case 2 -> {
                        SysRole role = sysRoleService.getById(api.getId());
                        configAttribute = new SecurityConfig(role.getRoleKey());
                    }
                    default -> throw new IllegalStateException("错误的类型: " + api.getType());
                }
                configAttributeList.add(configAttribute);
                AuthCache.put(api.getUrl(), configAttributeList);
            }
        }
        return AuthCache;
    }

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

    public Object get(String key) {
        return objectCache.get(key);
    }

    public void set(String key, Object obj) {
        objectCache.put(key, obj);
    }
}
