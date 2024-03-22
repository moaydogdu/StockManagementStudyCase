package com.study.stockmanagementstudycase.user.service.auth.impl;

import com.study.stockmanagementstudycase.auth.model.dto.request.RegisterRequest;
import com.study.stockmanagementstudycase.user.exception.UserAlreadyExistException;
import com.study.stockmanagementstudycase.user.mapper.UserDTOMapper;
import com.study.stockmanagementstudycase.user.mapper.UserMapper;
import com.study.stockmanagementstudycase.user.model.User;
import com.study.stockmanagementstudycase.user.model.entity.UserEntity;
import com.study.stockmanagementstudycase.user.repository.UserRepository;
import com.study.stockmanagementstudycase.user.service.auth.UserRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserRegisterServiceImpl implements UserRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(
            final RegisterRequest registerRequest
    ) {
        this.checkEmailAndPhoneNumberUniqueness(registerRequest);

        final UserEntity userEntityToBeSave = UserDTOMapper
                .mapForSaving(registerRequest);

        userEntityToBeSave.setPassword(
                passwordEncoder.encode(registerRequest.getPassword()));

        userRepository.save(userEntityToBeSave);

        return UserMapper.toDomainModel(userEntityToBeSave);
    }

    private void checkEmailAndPhoneNumberUniqueness(
            final RegisterRequest registerRequest
    ) {
        if (userRepository.existsUserEntityByEmailOrPhoneNumber(
                registerRequest.getEmail(), registerRequest.getPhoneNumber()))
        {
            throw new UserAlreadyExistException("There is an another user with given email address or phone number");
        }
    }
}
