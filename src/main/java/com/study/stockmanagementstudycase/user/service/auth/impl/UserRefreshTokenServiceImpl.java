package com.study.stockmanagementstudycase.user.service.auth.impl;

import com.study.stockmanagementstudycase.auth.exception.UserStatusNotValidException;
import com.study.stockmanagementstudycase.auth.model.Token;
import com.study.stockmanagementstudycase.auth.model.dto.request.TokenRefreshRequest;
import com.study.stockmanagementstudycase.auth.model.enums.TokenClaims;
import com.study.stockmanagementstudycase.auth.model.enums.UserStatus;
import com.study.stockmanagementstudycase.auth.service.TokenService;
import com.study.stockmanagementstudycase.user.exception.UserNotFoundException;
import com.study.stockmanagementstudycase.user.model.entity.UserEntity;
import com.study.stockmanagementstudycase.user.repository.UserRepository;
import com.study.stockmanagementstudycase.user.service.auth.UserRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserRefreshTokenServiceImpl implements UserRefreshTokenService {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    public Token refreshAccessToken(
            final TokenRefreshRequest tokenRefreshRequest
    ) {
        tokenService.verifyAndValidate(tokenRefreshRequest.getRefreshToken());

        final String userId = tokenService
                .getPayload(tokenRefreshRequest.getRefreshToken())
                .get(TokenClaims.USER_ID.getValue())
                .toString();

        final UserEntity userEntityFromDB = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("With given id: " + userId));

        this.validateUserStatus(userEntityFromDB.getUserStatus());

        return tokenService.generateToken(
                userEntityFromDB.getClaims(),
                tokenRefreshRequest.getRefreshToken()
        );
    }

    private void validateUserStatus(
            final UserStatus userStatus
    ) {
        if (userStatus != UserStatus.ACTIVE)
        {
            throw new UserStatusNotValidException("Current user status: " + userStatus);
        }
    }
}
