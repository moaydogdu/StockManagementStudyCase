package com.study.stockmanagementstudycase.security.service;

import com.study.stockmanagementstudycase.security.model.dto.request.AuthenticationRequest;
import com.study.stockmanagementstudycase.security.model.dto.request.RegisterRequest;
import com.study.stockmanagementstudycase.security.model.dto.response.AuthenticationResponse;
import com.study.stockmanagementstudycase.security.model.entity.TokenEntity;
import com.study.stockmanagementstudycase.security.model.entity.UserEntity;
import com.study.stockmanagementstudycase.security.model.enums.TokenType;
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

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    public void register(
            final RegisterRequest registerRequest
    ) {
        // TODO : Aynı email'e sahip bir başka kullanıcı varsa spesifik bir hata fırlat.
        // TODO : Buraya özel mapper yaz.

        final UserEntity user = UserEntity.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        userRepository.save(user);
    }

    public AuthenticationResponse authenticate(
            final AuthenticationRequest authenticationRequest
    ) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(), //Principal
                        authenticationRequest.getPassword() //Credential
                )
        );


        final UserEntity user = userRepository
                .findUserEntityByEmail(
                        authenticationRequest.getEmail()
                )
                .orElseThrow(RuntimeException::new);

        revokeAllUserTokens(user);

        final String accessToken = jwtService
                .generateToken(user);

        final String refreshToken = jwtService
                .generateRefreshToken(user);


        return AuthenticationResponse.builder()
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

            var user = userRepository.findUserEntityByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("Token'de oynama mevcut."));

            if (jwtService.isTokenValid(refreshToken, user)) {

                final String accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);

                return accessToken;
            }
        }

        throw new RuntimeException("Token'de oynama mevcut.");
    }


    private void saveUserToken(UserEntity user, String jwtToken) {
        final TokenEntity token = TokenEntity.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(UserEntity user) {

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
