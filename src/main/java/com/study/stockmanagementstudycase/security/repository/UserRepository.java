package com.study.stockmanagementstudycase.security.repository;

import com.study.stockmanagementstudycase.security.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findUserEntityByEmail(
            final String email
    );

    Optional<UserEntity> findUserEntityByEmailAndPassword(
            final String email,
            final String password
    );

    boolean existsUserEntitiesByEmail(
            final String email
    );
}
