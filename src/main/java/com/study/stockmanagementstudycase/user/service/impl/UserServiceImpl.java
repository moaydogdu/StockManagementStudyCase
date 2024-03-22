package com.study.stockmanagementstudycase.user.service.impl;

import com.study.stockmanagementstudycase.user.exception.UserNotFoundException;
import com.study.stockmanagementstudycase.user.mapper.UserMapper;
import com.study.stockmanagementstudycase.user.model.User;
import com.study.stockmanagementstudycase.user.model.entity.UserEntity;
import com.study.stockmanagementstudycase.user.repository.UserRepository;
import com.study.stockmanagementstudycase.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(
            final String userId
    ) {
        final UserEntity userEntityFromDB = userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("With given id: " + userId));

        return UserMapper.toDomainModel(userEntityFromDB);
    }
}
