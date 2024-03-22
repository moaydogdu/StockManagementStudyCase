package com.study.stockmanagementstudycase.auth.service.impl;

import com.study.stockmanagementstudycase.auth.exception.TokenAlreadyInvalidatedException;
import com.study.stockmanagementstudycase.auth.model.entity.InvalidTokenEntity;
import com.study.stockmanagementstudycase.auth.repository.InvalidTokenRepository;
import com.study.stockmanagementstudycase.auth.service.InvalidTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class InvalidTokenServiceImpl implements InvalidTokenService {

    private final InvalidTokenRepository invalidTokenRepository;

    @Override
    public void invalidateTokens(
            final Set<String> tokenIds
    ) {
        final Set<InvalidTokenEntity> invalidTokenEntities = tokenIds.stream()
                .map(tokenId -> InvalidTokenEntity.builder()
                        .tokenId(tokenId)
                        .build()
                )
                .collect(Collectors.toSet());

        invalidTokenRepository.saveAll(invalidTokenEntities);
    }

    @Override
    public void checkForInvalidityOfToken(
            final String tokenId
    ) {
        final boolean isTokenValid = invalidTokenRepository.findByTokenId(tokenId)
                .isPresent();

        if (isTokenValid)
        {
            throw new TokenAlreadyInvalidatedException(tokenId);
        }
    }
}
