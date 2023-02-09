package com.example.makalu.service.impl;

import com.example.makalu.domain.Sprint;
import com.example.makalu.dto.project.StatusPayload;
import com.example.makalu.dto.sprint.SprintPayload;
import com.example.makalu.dto.sprint.SprintResponse;
import com.example.makalu.mapper.SprintMapper;
import com.example.makalu.repository.SprintRepository;
import com.example.makalu.service.SprintService;
import com.example.makalu.service.exception.DomainNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class SprintServiceImpl implements SprintService {

    private final SprintRepository sprintRepository;

    public SprintServiceImpl(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    @Override
    @Transactional
    public SprintResponse update(Long id, SprintPayload payload) {
        try {
            Sprint sprint = sprintRepository.getReferenceById(id);
            SprintMapper.INSTANCE.updateSprintFromDto(payload, sprint);
            Sprint updatedSprint = sprintRepository.save(sprint);
            return SprintMapper.INSTANCE.toSprintResponse(updatedSprint);
        } catch (EntityNotFoundException e) {
            throw new DomainNotFoundException("Sprint not found");
        }
    }

    @Override
    @Transactional
    public SprintResponse changeStatus(Long id, StatusPayload payload) {
        try {
            Sprint sprint = sprintRepository.getReferenceById(id);
            sprint.setStatus(payload.status());
            Sprint updatedSprint = sprintRepository.save(sprint);
            return SprintMapper.INSTANCE.toSprintResponse(updatedSprint);
        } catch (EntityNotFoundException e) {
            throw new DomainNotFoundException("Project not found");
        }
    }

}
