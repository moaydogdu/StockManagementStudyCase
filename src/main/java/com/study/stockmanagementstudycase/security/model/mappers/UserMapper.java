package com.study.stockmanagementstudycase.security.model.mappers;

import com.study.stockmanagementstudycase.security.model.User;
import com.study.stockmanagementstudycase.security.model.dto.request.RegisterRequest;
import com.study.stockmanagementstudycase.security.model.entity.UserEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;


public class UserMapper {

    private final static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static UserEntity mapForSaving(
            final RegisterRequest registerRequest
    ) {
        return UserEntity.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
    }

    public static User toDomainModel(
            final UserEntity userEntity
    ) {
        return User.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .password(passwordEncoder.encode(userEntity.getPassword()))
                .createdAt(userEntity.getCreatedAt())
                .updatedAt(userEntity.getUpdatedAt())
                .build();
    }

    public static UserEntity toEntity(
            final User user
    ) {
        if (Objects.isNull(user)) {
            return null;
        }
        return UserEntity.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .createdAt(user.createdAt)
                .updatedAt(user.updatedAt)
                .build();
    }
}
