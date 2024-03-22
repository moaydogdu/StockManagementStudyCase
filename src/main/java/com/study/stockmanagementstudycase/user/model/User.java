package com.study.stockmanagementstudycase.user.model;

import com.study.stockmanagementstudycase.auth.model.enums.UserStatus;
import com.study.stockmanagementstudycase.auth.model.enums.UserType;
import com.study.stockmanagementstudycase.common.model.BaseDomainModel;
import com.study.stockmanagementstudycase.user.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Domain model of {@link UserEntity}
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseDomainModel {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private UserType userType;
    private UserStatus userStatus;

}
