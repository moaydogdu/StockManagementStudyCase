package com.study.stockmanagementstudycase.user.controller;

import com.study.stockmanagementstudycase.auth.mapper.TokenDTOMapper;
import com.study.stockmanagementstudycase.auth.model.Token;
import com.study.stockmanagementstudycase.auth.model.dto.request.LoginRequest;
import com.study.stockmanagementstudycase.auth.model.dto.request.RegisterRequest;
import com.study.stockmanagementstudycase.auth.model.dto.request.TokenInvalidateRequest;
import com.study.stockmanagementstudycase.auth.model.dto.request.TokenRefreshRequest;
import com.study.stockmanagementstudycase.auth.model.dto.response.TokenResponse;
import com.study.stockmanagementstudycase.common.model.dto.CustomResponse;
import com.study.stockmanagementstudycase.user.service.auth.UserLoginService;
import com.study.stockmanagementstudycase.user.service.auth.UserLogoutService;
import com.study.stockmanagementstudycase.user.service.auth.UserRefreshTokenService;
import com.study.stockmanagementstudycase.user.service.auth.UserRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication/users")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserRegisterService userRegisterService;
    private final UserLoginService userLoginService;
    private final UserRefreshTokenService userRefreshTokenService;
    private final UserLogoutService userLogoutService;

    @PostMapping("/register")
    public CustomResponse<Void> register(
            @RequestBody @Valid final RegisterRequest registerRequest
    ) {
        userRegisterService.register(registerRequest);

        return CustomResponse.SUCCESS;
    }

    @PostMapping("/login")
    public CustomResponse<TokenResponse> login(
            @RequestBody @Valid final LoginRequest loginRequest
    ) {
        final Token token = userLoginService
                .login(loginRequest);

        final TokenResponse tokenResponse = TokenDTOMapper
                .toResponse(token);

        return CustomResponse.ok(tokenResponse);
    }

    @PostMapping("/refresh-token")
    public CustomResponse<TokenResponse> refreshToken(
            @RequestBody @Valid final TokenRefreshRequest tokenRefreshRequest
    ) {
        final Token createdAccessToken = userRefreshTokenService
                .refreshAccessToken(tokenRefreshRequest);

        final TokenResponse tokenResponse = TokenDTOMapper
                .toResponse(createdAccessToken);

        return CustomResponse.ok(tokenResponse);
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    public CustomResponse<Void> logout(
            @RequestBody @Valid final TokenInvalidateRequest tokenInvalidateRequest
    ) {
        userLogoutService.logout(tokenInvalidateRequest);

        return CustomResponse.SUCCESS;
    }
}
