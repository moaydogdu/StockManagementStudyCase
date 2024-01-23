package com.study.stockmanagementstudycase.security.model;

import com.study.stockmanagementstudycase.common.model.BaseDomainModel;
import com.study.stockmanagementstudycase.security.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Domain model of {@link UserEntity}
 */

@SuperBuilder
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseDomainModel {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
