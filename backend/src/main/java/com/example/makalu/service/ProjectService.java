package com.example.makalu.service;

import com.example.makalu.dto.project.ProjectPayload;
import com.example.makalu.dto.project.ProjectResponse;
import com.example.makalu.dto.project.StatusPayload;

public interface ProjectService {

    ProjectResponse createProject(ProjectPayload payload);

    ProjectResponse update(Long id, ProjectPayload payload);

    ProjectResponse changeStatus(Long id, StatusPayload payload);

}
