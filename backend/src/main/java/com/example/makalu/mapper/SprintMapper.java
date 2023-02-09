package com.example.makalu.mapper;

import com.example.makalu.domain.Sprint;
import com.example.makalu.dto.sprint.SprintPayload;
import com.example.makalu.dto.sprint.SprintResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SprintMapper {

    SprintMapper INSTANCE = Mappers.getMapper(SprintMapper.class);

    Sprint toSprint(SprintPayload payload);

    SprintResponse toSprintGetRequestBody(Sprint sprint);

}
