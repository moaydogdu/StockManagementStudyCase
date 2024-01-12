package com.study.stockmanagementstudycase.security.controller;

import com.study.stockmanagementstudycase.security.model.dto.request.AuthenticationRequest;
import com.study.stockmanagementstudycase.security.model.dto.request.RegisterRequest;
import com.study.stockmanagementstudycase.security.model.dto.response.AuthenticationResponse;
import com.study.stockmanagementstudycase.security.service.AuthenticationService;
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

    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @RequestBody @Valid final RegisterRequest request
    ) {
        authenticationService.register(request);
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
            final HttpServletRequest request
    ) {
        final String accessToken = authenticationService
                .refreshToken(request);

        return ResponseEntity.ok(accessToken);
    }

    // TODO : LOGOUT
    // TODO : OPTIMIZASYON.

}
