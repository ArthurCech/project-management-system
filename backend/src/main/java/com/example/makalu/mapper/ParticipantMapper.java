package com.example.makalu.mapper;

import com.example.makalu.domain.Participant;
import com.example.makalu.dto.participant.ParticipantResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {

    ParticipantMapper INSTANCE = Mappers.getMapper(ParticipantMapper.class);

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "role", source = "role")
    ParticipantResponse toParticipantResponse(Participant participant);

}
