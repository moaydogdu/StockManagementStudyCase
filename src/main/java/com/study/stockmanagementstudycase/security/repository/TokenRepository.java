package com.study.stockmanagementstudycase.security.repository;

import com.study.stockmanagementstudycase.security.model.entity.TokenEntity;
import com.study.stockmanagementstudycase.security.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, String> {
    Optional<TokenEntity> findTokenEntityByToken(
            final String token
    );

    List<TokenEntity> findTokenEntitiesByUserAndExpiredIsFalseAndRevokedIsFalse(
            final UserEntity user
    );

}
