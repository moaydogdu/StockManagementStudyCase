package com.study.stockmanagementstudycase.security.service;

import com.study.stockmanagementstudycase.security.exception.token.TokenInterferedException;
import com.study.stockmanagementstudycase.security.model.dto.response.AuthenticationResponse;
import com.study.stockmanagementstudycase.security.model.dto.response.GenerateTokenResponse;
import com.study.stockmanagementstudycase.security.model.entity.TokenEntity;
import com.study.stockmanagementstudycase.security.model.entity.UserEntity;
import com.study.stockmanagementstudycase.security.model.mappers.TokenMapper;
import com.study.stockmanagementstudycase.security.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;
    private final UserService userService;
    private final JWTService jwtService;

    public GenerateTokenResponse generateToken(
            final UserEntity userEntity
    ) {
        this.revokeAllUserTokens(userEntity);

        final String accessToken = jwtService
                .generateToken(userEntity);

        saveUserToken(userEntity, accessToken);

        final String refreshToken = jwtService
                .generateRefreshToken(userEntity);

        saveUserToken(userEntity, refreshToken);

        return GenerateTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String refreshToken(
            final HttpServletRequest request
    ) {

        final String refreshToken = jwtService.getJwtFromHeader(request);

        final String userEmail = jwtService.extractUsername(refreshToken);

        if (userEmail != null) {

            var user = userService.loadUserByUsername(userEmail);

            if (jwtService.isTokenValid(refreshToken, user)) {

                final String accessToken = jwtService.generateToken(user);
                revokeAllUserTokens((UserEntity) user);

                return accessToken;
            }
        }

        throw new TokenInterferedException();
    }

    private void saveUserToken(
            final UserEntity userEntity,
            final String jwtToken
    ) {
        final TokenEntity userTokenToBeCreate = TokenMapper.mapForSaving(
                userEntity, jwtToken
        );
        tokenRepository.save(userTokenToBeCreate);
    }

    private void revokeAllUserTokens(
            final UserEntity user
    ) {
        List<TokenEntity> validUserTokens = tokenRepository
                .findTokenEntitiesByUserAndExpiredIsFalseAndRevokedIsFalse(user);

        if (validUserTokens.isEmpty()) {
            return;
        }

        validUserTokens.forEach(
                token -> {
                    token.setExpired(true);
                    token.setRevoked(true);
                }
        );

        tokenRepository.saveAll(validUserTokens);
    }

}
