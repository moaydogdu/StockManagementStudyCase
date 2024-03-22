package com.study.stockmanagementstudycase.user.service.auth.impl;

import com.study.stockmanagementstudycase.auth.model.CustomIdentity;
import com.study.stockmanagementstudycase.auth.model.dto.request.TokenInvalidateRequest;
import com.study.stockmanagementstudycase.auth.service.InvalidTokenService;
import com.study.stockmanagementstudycase.auth.service.TokenService;
import com.study.stockmanagementstudycase.user.service.auth.UserLogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
class UserLogoutServiceImpl implements UserLogoutService {

    private final TokenService tokenService;
    private final InvalidTokenService invalidTokenService;
    private final CustomIdentity customIdentity;

    @Override
    public void logout(
            final TokenInvalidateRequest tokenInvalidateRequest
    ) {
        final String accessToken = customIdentity.getAccessToken();

        this.verifyAndValidateTokens(
                accessToken,
                tokenInvalidateRequest.getRefreshToken());

        final String accessTokenId = this.checkForInvalidityOfTokenAndGetTokenId(accessToken);

        final String refreshTokenId = this.checkForInvalidityOfTokenAndGetTokenId(
                tokenInvalidateRequest.getRefreshToken());

        invalidTokenService.invalidateTokens(Set.of(accessTokenId,refreshTokenId));
    }

    private void verifyAndValidateTokens(
            final String accessToken,
            final String refreshToken
    ) {
        tokenService.verifyAndValidate(Set.of(accessToken,refreshToken));
    }

    private String checkForInvalidityOfTokenAndGetTokenId(
            final String token
    ) {
        final String tokenId = tokenService
                .getPayload(token)
                .getId();

        invalidTokenService.checkForInvalidityOfToken(tokenId);

        return tokenId;
    }
}
