package com.study.stockmanagementstudycase.auth.model;

import com.study.stockmanagementstudycase.auth.model.enums.TokenClaims;
import com.study.stockmanagementstudycase.user.model.User;
import com.study.stockmanagementstudycase.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
@Scope(
        value = "request",
        proxyMode = ScopedProxyMode.TARGET_CLASS
)
@RequiredArgsConstructor
public class CustomIdentity {

    private final UserService userService;

    public User getUser() {
        return userService.getUserById(this.getUserId());
    }

    public String getEmail() {
        return this.getJwt().getClaim(TokenClaims.USER_EMAIL.getValue());
    }

    public String getAccessToken() {
        return this.getJwt().getTokenValue();
    }

    public String getUserId() {
        return this.getJwt().getClaim(TokenClaims.USER_ID.getValue());
    }

    private Jwt getJwt() {
        return ((Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public boolean isAuthenticated() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser");
    }

}
