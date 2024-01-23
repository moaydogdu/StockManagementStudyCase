package com.study.stockmanagementstudycase.security.controller;

import com.study.stockmanagementstudycase.security.model.dto.request.AuthenticationRequest;
import com.study.stockmanagementstudycase.security.model.dto.request.RegisterRequest;
import com.study.stockmanagementstudycase.security.model.dto.response.AuthenticationResponse;
import com.study.stockmanagementstudycase.security.service.AuthenticationService;
import com.study.stockmanagementstudycase.security.service.RegisterService;
import com.study.stockmanagementstudycase.security.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final RegisterService registerService;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @RequestBody @Valid final RegisterRequest registerRequest
    ) {
        registerService.register(registerRequest);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody final AuthenticationRequest authenticationRequest
    ) {
        final AuthenticationResponse authenticationResponse = authenticationService
                .authenticate(authenticationRequest);

        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<String> refreshToken(
            final HttpServletRequest refreshTokenRequest
    ) {
        final String accessToken = tokenService
                .generateAccessTokenUsingByRefreshToken(refreshTokenRequest);

        return ResponseEntity.ok(accessToken);
    }

}
