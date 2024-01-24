package com.study.stockmanagementstudycase.security.service;

import com.study.stockmanagementstudycase.security.model.AccessAndRefreshToken;
import com.study.stockmanagementstudycase.security.model.User;
import com.study.stockmanagementstudycase.security.model.dto.request.AuthenticationRequest;
import com.study.stockmanagementstudycase.security.model.dto.response.AuthenticationResponse;
import com.study.stockmanagementstudycase.security.model.entity.UserEntity;
import com.study.stockmanagementstudycase.security.model.mappers.TokenDTOMapper;
import com.study.stockmanagementstudycase.security.model.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

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

        User user = userService.getUserByEmail(authenticationRequest.getEmail());

        UserEntity userEntity = UserMapper.toEntity(user);

        final AccessAndRefreshToken accessAndRefreshToken = tokenService
                .generateToken(userEntity);

        return TokenDTOMapper.toAuthenticationResponse(accessAndRefreshToken);
    }
}
