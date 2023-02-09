package com.example.makalu.service.impl;

import com.example.makalu.domain.Participant;
import com.example.makalu.domain.Project;
import com.example.makalu.domain.Sprint;
import com.example.makalu.domain.User;
import com.example.makalu.domain.enums.Status;
import com.example.makalu.domain.pk.ParticipantPK;
import com.example.makalu.dto.participant.DeleteParticipantPayload;
import com.example.makalu.dto.participant.ParticipantResponse;
import com.example.makalu.dto.participant.ParticipantsPayload;
import com.example.makalu.dto.participant.ParticipantsResponse;
import com.example.makalu.dto.project.ProjectPayload;
import com.example.makalu.dto.project.ProjectResponse;
import com.example.makalu.dto.project.StatusPayload;
import com.example.makalu.dto.sprint.SprintPayload;
import com.example.makalu.dto.sprint.SprintResponse;
import com.example.makalu.mapper.ParticipantMapper;
import com.example.makalu.mapper.ProjectMapper;
import com.example.makalu.mapper.SprintMapper;
import com.example.makalu.repository.ParticipantRepository;
import com.example.makalu.repository.ProjectRepository;
import com.example.makalu.repository.SprintRepository;
import com.example.makalu.repository.UserRepository;
import com.example.makalu.service.ProjectService;
import com.example.makalu.service.exception.DomainNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ParticipantRepository participantRepository;
    private final SprintRepository sprintRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              UserRepository userRepository,
                              ParticipantRepository participantRepository,
                              SprintRepository sprintRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.participantRepository = participantRepository;
        this.sprintRepository = sprintRepository;
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

    @Override
    @Transactional
    public ParticipantsResponse addParticipant(Long id, ParticipantsPayload payload) {
        try {
            Project project = projectRepository.getReferenceById(id);
            List<Participant> participantsToBeSaved = payload.participants().stream()
                    .map(participantPayload -> {
                        User user = userRepository.findByEmail(participantPayload.email())
                                .orElseThrow(() -> new DomainNotFoundException("User not found"));
                        return new Participant(user, project, participantPayload.role());
                    })
                    .toList();
            List<Participant> savedParticipants = participantRepository.saveAll(participantsToBeSaved);
            List<ParticipantResponse> participantsList = savedParticipants.stream()
                    .map(ParticipantMapper.INSTANCE::toParticipantResponse)
                    .toList();
            return new ParticipantsResponse(project.getId(), project.getName(), project.getDescription(),
                    project.getStatus(), participantsList);
        } catch (EntityNotFoundException e) {
            throw new DomainNotFoundException("Project not found");
        }
    }

    @Override
    public void deleteParticipant(Long id, DeleteParticipantPayload payload) {
        try {
            User user = userRepository.getReferenceById(payload.id());
            Project project = projectRepository.getReferenceById(id);
            participantRepository.deleteById(new ParticipantPK(user, project));
        } catch (JpaSystemException e) {
            throw new DomainNotFoundException("User or project invalid");
        }
    }

    @Override
    @Transactional
    public SprintResponse addSprintToProject(Long id, SprintPayload payload) {
        try {
            Sprint sprintToBeCreated = SprintMapper.INSTANCE.toSprint(payload);
            Project project = projectRepository.getReferenceById(id);
            sprintToBeCreated.setProject(project);
            sprintToBeCreated.setStatus(Status.NOT_STARTED);
            sprintToBeCreated.setProgress(0);
            Sprint createdSprint = sprintRepository.save(sprintToBeCreated);
            return SprintMapper.INSTANCE.toSprintGetRequestBody(createdSprint);
        } catch (DataAccessException e) {
            throw new DomainNotFoundException("Project not found");
        }
    }

}
