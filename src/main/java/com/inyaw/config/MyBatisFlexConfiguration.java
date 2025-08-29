package com.inyaw.config;

import com.mybatisflex.core.audit.AuditManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Aizmr
 * @CreateTime: 2025-04-12
 * @Description:
 */
@Configuration
@Slf4j
public class MyBatisFlexConfiguration {

    public MyBatisFlexConfiguration() {
        //开启审计功能
        AuditManager.setAuditEnable(true);

        //设置 SQL 审计收集器
        AuditManager.setMessageCollector(auditMessage -> {
                    if (auditMessage.getElapsedTime() > 0) {
                        log.info("{},{}ms", auditMessage.getFullSql()
                                , auditMessage.getElapsedTime());
                    }
                }
        );
    }
}
