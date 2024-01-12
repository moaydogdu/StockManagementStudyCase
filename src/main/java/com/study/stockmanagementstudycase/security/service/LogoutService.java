package com.study.stockmanagementstudycase.security.service;

import com.study.stockmanagementstudycase.security.model.entity.TokenEntity;
import com.study.stockmanagementstudycase.security.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        final String jwt = authHeader.substring(7);

        final Optional<TokenEntity> storedToken = tokenRepository.findTokenEntityByToken(jwt);

        if (storedToken.isPresent()) {
            TokenEntity tokenEntity = storedToken.get();
            tokenEntity.setExpired(true);
            tokenEntity.setRevoked(true);
            tokenRepository.save(tokenEntity);
            SecurityContextHolder.clearContext();
        }
    }
}
