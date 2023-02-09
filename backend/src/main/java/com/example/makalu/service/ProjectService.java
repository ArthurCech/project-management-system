package com.example.makalu.service;

import com.example.makalu.dto.participant.DeleteParticipantPayload;
import com.example.makalu.dto.participant.ParticipantsPayload;
import com.example.makalu.dto.participant.ParticipantsResponse;
import com.example.makalu.dto.project.ProjectPayload;
import com.example.makalu.dto.project.ProjectResponse;
import com.example.makalu.dto.project.StatusPayload;

public interface ProjectService {

    ProjectResponse createProject(ProjectPayload payload);

    ProjectResponse update(Long id, ProjectPayload payload);

    ProjectResponse changeStatus(Long id, StatusPayload payload);

    ParticipantsResponse addParticipant(Long id, ParticipantsPayload payload);

    void deleteParticipant(Long id, DeleteParticipantPayload payload);

}
