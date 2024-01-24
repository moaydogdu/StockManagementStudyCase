package com.study.stockmanagementstudycase.security.service;

import com.study.stockmanagementstudycase.security.exception.user.UserNotFoundException;
import com.study.stockmanagementstudycase.security.model.User;
import com.study.stockmanagementstudycase.security.model.entity.UserEntity;
import com.study.stockmanagementstudycase.security.model.mappers.UserMapper;
import com.study.stockmanagementstudycase.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(
            final String email
    ) {
        return userRepository
                .findUserEntityByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }


    public User getUserByEmail(
            final String email
    ) {
        final UserEntity userEntityFromDb = userRepository
                .findUserEntityByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        return UserMapper.toDomainModel(userEntityFromDb);
    }
}
