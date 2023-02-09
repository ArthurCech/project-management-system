package com.example.makalu.service;

import com.example.makalu.dto.project.StatusPayload;
import com.example.makalu.dto.sprint.SprintPayload;
import com.example.makalu.dto.sprint.SprintResponse;

public interface SprintService {

    SprintResponse update(Long id, SprintPayload payload);

    SprintResponse changeStatus(Long id, StatusPayload payload);

}
