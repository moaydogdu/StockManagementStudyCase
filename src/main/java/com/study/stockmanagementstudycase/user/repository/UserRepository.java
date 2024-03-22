package com.study.stockmanagementstudycase.user.repository;

import com.study.stockmanagementstudycase.user.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findUserEntityByEmail(
            final String email
    );


    boolean existsUserEntityByEmailOrPhoneNumber(
            final String email,
            final String phoneNumber
    );

}
