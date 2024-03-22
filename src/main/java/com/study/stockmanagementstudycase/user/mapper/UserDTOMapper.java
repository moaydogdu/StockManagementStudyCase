package com.study.stockmanagementstudycase.user.mapper;

import com.study.stockmanagementstudycase.auth.model.dto.request.RegisterRequest;
import com.study.stockmanagementstudycase.auth.model.enums.UserStatus;
import com.study.stockmanagementstudycase.auth.model.enums.UserType;
import com.study.stockmanagementstudycase.user.model.entity.UserEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserDTOMapper {
    public UserEntity mapForSaving(
            final RegisterRequest registerRequest
    ) {
        return UserEntity.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .phoneNumber(registerRequest.getPhoneNumber())
                .userStatus(UserStatus.ACTIVE)
                .userType(UserType.USER)
                .build();
    }
}
