package com.study.stockmanagementstudycase.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@RequiredArgsConstructor
public class Identity {

    private static final String UNAUTHENTICATED = "anonymousUser";

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.isAuthenticated() && !authentication.getName().equals(UNAUTHENTICATED);
    }

    public String getEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
