package com.example.makalu.service.impl;

import com.example.makalu.domain.Project;
import com.example.makalu.domain.User;
import com.example.makalu.domain.enums.Status;
import com.example.makalu.dto.project.ProjectPayload;
import com.example.makalu.dto.project.ProjectResponse;
import com.example.makalu.dto.project.StatusPayload;
import com.example.makalu.mapper.ProjectMapper;
import com.example.makalu.repository.ProjectRepository;
import com.example.makalu.repository.UserRepository;
import com.example.makalu.service.ProjectService;
import com.example.makalu.service.exception.DomainNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public ProjectResponse createProject(ProjectPayload payload) {
        try {
            Project projectToBeCreated = ProjectMapper.INSTANCE.toProject(payload);
            User user = userRepository.getReferenceById(1L);
            // TODO: get authenticated user to set the project owner
            projectToBeCreated.setOwner(user);
            projectToBeCreated.setStatus(Status.NOT_STARTED);
            projectToBeCreated.setProgress(0);
            Project createdProject = projectRepository.save(projectToBeCreated);
            return ProjectMapper.INSTANCE.toProjectResponse(createdProject);
        } catch (DataAccessException e) {
            throw new DomainNotFoundException("User not found");
        }
    }

    @Override
    @Transactional
    public ProjectResponse update(Long id, ProjectPayload payload) {
        try {
            Project project = projectRepository.getReferenceById(id);
            ProjectMapper.INSTANCE.updateProjectFromDto(payload, project);
            Project updatedProject = projectRepository.save(project);
            return ProjectMapper.INSTANCE.toProjectResponse(updatedProject);
        } catch (EntityNotFoundException e) {
            throw new DomainNotFoundException("Project not found");
        }
    }

    @Override
    @Transactional
    public ProjectResponse changeStatus(Long id, StatusPayload payload) {
        try {
            Project project = projectRepository.getReferenceById(id);
            project.setStatus(payload.status());
            Project updatedProject = projectRepository.save(project);
            return ProjectMapper.INSTANCE.toProjectResponse(updatedProject);
        } catch (EntityNotFoundException e) {
            throw new DomainNotFoundException("Project not found");
        }
    }

}
