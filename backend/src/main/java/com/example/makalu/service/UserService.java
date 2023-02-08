package com.example.makalu.service;

import com.example.makalu.dto.user.UserCreatePayload;
import com.example.makalu.dto.user.UserResponse;
import com.example.makalu.dto.user.UserUpdatePayload;

public interface UserService {

    UserResponse create(UserCreatePayload payload);

    UserResponse update(Long id, UserUpdatePayload payload);

}
