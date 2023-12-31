package com.inyaw.config;

import com.inyaw.sys.bean.InyawSysApi;
import com.inyaw.sys.bean.InyawSysConfig;
import com.inyaw.sys.bean.InyawSysRole;
import com.inyaw.sys.dao.InyawSysApiDao;
import com.inyaw.sys.service.InyawSysConfigService;
import com.inyaw.sys.service.InyawSysRoleService;
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

    private final InyawSysApiDao inyawSysApiDao;
    private final InyawSysRoleService inyawSysRoleService;
    private final InyawSysConfigService inyawSysConfigService;

    public Map<String, Collection<ConfigAttribute>> getConfigAttributeMap() {
        if (AuthCache.size() < 1) {
            List<InyawSysApi> list = inyawSysApiDao.findAll();
            for (InyawSysApi api : list) {
                List<ConfigAttribute> configAttributeList = new ArrayList<>();
                ConfigAttribute configAttribute;
                switch (api.getType()) {
                    case 0 -> configAttribute = new SecurityConfig("ROLE_ANY");
                    case 1 -> configAttribute = new SecurityConfig("ROLE_LOGIN");
                    case 2 -> {
                        InyawSysRole role = inyawSysRoleService.getById(api.getId());
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
        if (configMap == null || configMap.size() < 1) {
            configMap = new HashMap<>();
            List<InyawSysConfig> configList = inyawSysConfigService.findAll(type);
            for (InyawSysConfig bean : configList) {
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
