package com.example.makalu.mapper;

import com.example.makalu.domain.Project;
import com.example.makalu.dto.project.ProjectPayload;
import com.example.makalu.dto.project.ProjectResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    Project toProject(ProjectPayload payload);

    ProjectResponse toProjectResponse(Project project);

    void updateProjectFromDto(ProjectPayload payload, @MappingTarget Project project);

}
