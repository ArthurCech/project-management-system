package com.example.makalu.service.impl;

import com.example.makalu.domain.User;
import com.example.makalu.dto.user.UserCreatePayload;
import com.example.makalu.dto.user.UserResponse;
import com.example.makalu.dto.user.UserUpdatePayload;
import com.example.makalu.mapper.UserMapper;
import com.example.makalu.repository.UserRepository;
import com.example.makalu.service.UserService;
import com.example.makalu.service.exception.DomainNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
// TODO: get profile via authenticated user
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserResponse create(UserCreatePayload payload) {
        // TODO: encrypt password with bcrypt
        User userToBeCreated = UserMapper.INSTANCE.toUser(payload);
        User createdUser = userRepository.save(userToBeCreated);
        return UserMapper.INSTANCE.toUserResponse(createdUser);
    }

    @Override
    @Transactional
    public UserResponse update(Long id, UserUpdatePayload payload) {
        // TODO: encrypt password with bcrypt
        try {
            User user = userRepository.getReferenceById(id);
            UserMapper.INSTANCE.updateUserFromDto(payload, user);
            User updatedUser = userRepository.save(user);
            return UserMapper.INSTANCE.toUserResponse(updatedUser);
        } catch (EntityNotFoundException e) {
            throw new DomainNotFoundException("User not found");
        }
    }

}
