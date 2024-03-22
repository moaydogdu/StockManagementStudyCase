package com.study.stockmanagementstudycase.user.service;

import com.study.stockmanagementstudycase.user.model.User;

public interface UserService {

    User getUserById(
            final String userId
    );

}
