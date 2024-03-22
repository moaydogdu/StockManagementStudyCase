package com.study.stockmanagementstudycase.user.service.auth.impl;

import com.study.stockmanagementstudycase.auth.exception.PasswordNotValidException;
import com.study.stockmanagementstudycase.auth.exception.UserStatusNotValidException;
import com.study.stockmanagementstudycase.auth.model.Token;
import com.study.stockmanagementstudycase.auth.model.dto.request.LoginRequest;
import com.study.stockmanagementstudycase.auth.model.enums.UserStatus;
import com.study.stockmanagementstudycase.auth.service.TokenService;
import com.study.stockmanagementstudycase.user.exception.UserNotFoundException;
import com.study.stockmanagementstudycase.user.model.entity.UserEntity;
import com.study.stockmanagementstudycase.user.repository.UserRepository;
import com.study.stockmanagementstudycase.user.service.auth.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserLoginServiceImpl implements UserLoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Override
    public Token login(
            final LoginRequest loginRequest
    ) {
        final UserEntity userEntityFromDB = userRepository
                .findUserEntityByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UserNotFoundException("With given email address: "
                        + loginRequest.getEmail()));

        if (!passwordEncoder.matches(
                loginRequest.getPassword(),
                userEntityFromDB.getPassword()))
        {
            throw new PasswordNotValidException();
        }

        this.validateUserStatus(userEntityFromDB.getUserStatus());

        return tokenService.generateToken(userEntityFromDB.getClaims());
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
