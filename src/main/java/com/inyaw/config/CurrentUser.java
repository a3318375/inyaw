package com.inyaw.config;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

/**
 * @Author: Aizmr
 * @CreateTime: 2025-08-05
 * @Description:
 */
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal(expression = "claims['sub']")
public @interface CurrentUser {
}
