package com.inyaw.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @author: yuxh
 * @date: 2021/4/5 0:43
 */
@Service
@RequiredArgsConstructor
public class InyaaAccessDecisionManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final SecurityMetadataSource securityMetadataSource;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        Collection<ConfigAttribute> collection = this.securityMetadataSource.getAttributes(context);
        // 遍历角色
        for (ConfigAttribute ca : collection) {
            // ① 当前url请求需要的权限
            String needRole = ca.getAttribute();
            if ("ROLE_ANY".equals(needRole)) {
                return new AuthorizationDecision(true);
            } else {
                // ② 当前用户所具有的角色
                Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();
                for (GrantedAuthority authority : authorities) {
                    if ("ROLE_ANONYMOUS".equals(authority.getAuthority())) {
                        return new AuthorizationDecision(false);
                    } else {
                        return new AuthorizationDecision(true);
                    }
                }
            }
        }
        return new AuthorizationDecision(false);
    }
}
