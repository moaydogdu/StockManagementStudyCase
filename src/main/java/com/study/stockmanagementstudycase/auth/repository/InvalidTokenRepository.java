package com.study.stockmanagementstudycase.auth.repository;

import com.study.stockmanagementstudycase.auth.model.entity.InvalidTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvalidTokenRepository extends JpaRepository<InvalidTokenEntity, String> {

    Optional<InvalidTokenEntity> findByTokenId(
            final String tokenId
    );

}
