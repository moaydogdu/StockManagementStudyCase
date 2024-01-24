package com.study.stockmanagementstudycase.security.service;

import com.study.stockmanagementstudycase.security.exception.email.EmailAlreadyExistException;
import com.study.stockmanagementstudycase.security.model.dto.request.RegisterRequest;
import com.study.stockmanagementstudycase.security.model.entity.UserEntity;
import com.study.stockmanagementstudycase.security.model.mappers.UserMapper;
import com.study.stockmanagementstudycase.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;

    public void register(
            final RegisterRequest registerRequest
    ) {
        this.checkEmailUniqueness(
                registerRequest.getEmail()
        );

        final UserEntity userEntityToBeCreate = UserMapper.mapForSaving(
                registerRequest
        );

        userRepository.save(userEntityToBeCreate);
    }

    private void checkEmailUniqueness(
            final String email
    ) {
        if (Boolean.TRUE.equals(userRepository
                .existsUserEntityByEmail(email))) {
            throw new EmailAlreadyExistException();
        }
    }
}
