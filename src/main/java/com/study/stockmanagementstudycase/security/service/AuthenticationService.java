package com.study.stockmanagementstudycase.security.service;

import com.study.stockmanagementstudycase.security.exception.email.EmailNotFoundException;
import com.study.stockmanagementstudycase.security.exception.token.TokenInterferedException;
import com.study.stockmanagementstudycase.security.model.User;
import com.study.stockmanagementstudycase.security.model.dto.request.AuthenticationRequest;
import com.study.stockmanagementstudycase.security.model.dto.request.RegisterRequest;
import com.study.stockmanagementstudycase.security.model.dto.response.AuthenticationResponse;
import com.study.stockmanagementstudycase.security.model.entity.TokenEntity;
import com.study.stockmanagementstudycase.security.model.entity.UserEntity;
import com.study.stockmanagementstudycase.security.model.enums.TokenType;
import com.study.stockmanagementstudycase.security.model.mappers.TokenDTOMapper;
import com.study.stockmanagementstudycase.security.model.mappers.TokenMapper;
import com.study.stockmanagementstudycase.security.model.mappers.UserMapper;
import com.study.stockmanagementstudycase.security.repository.TokenRepository;
import com.study.stockmanagementstudycase.security.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationResponse authenticate(
            final AuthenticationRequest authenticationRequest
    ) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(), //Principal
                        authenticationRequest.getPassword() //Credential
                )
        );

        User user = userService.getUserByUsername(authenticationRequest.getEmail());

        UserEntity userEntity = UserMapper.toEntity(user);

        AuthenticationResponse authenticationResponse = TokenDTOMapper
                .toAuthenticationResponse(tokenService.generateToken(userEntity));

        return authenticationResponse;
    }
}
