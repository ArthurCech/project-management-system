package com.example.makalu.mapper;

import com.example.makalu.domain.User;
import com.example.makalu.dto.user.UserCreatePayload;
import com.example.makalu.dto.user.UserResponse;
import com.example.makalu.dto.user.UserUpdatePayload;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserCreatePayload payload);

    UserResponse toUserResponse(User user);

    void updateUserFromDto(UserUpdatePayload payload, @MappingTarget User user);

}
