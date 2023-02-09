package com.example.makalu.controller;

import com.example.makalu.dto.project.StatusPayload;
import com.example.makalu.dto.sprint.SprintPayload;
import com.example.makalu.dto.sprint.SprintResponse;
import com.example.makalu.service.SprintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/sprints")
public class SprintController {

    private final SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<SprintResponse> updateSprint(@PathVariable Long id,
                                                       @RequestBody @Valid SprintPayload payload) {
        SprintResponse response = sprintService.update(id, payload);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SprintResponse> changeSprintStatus(@PathVariable Long id,
                                                             @RequestBody @Valid StatusPayload payload) {
        SprintResponse response = sprintService.changeStatus(id, payload);
        return ResponseEntity.ok(response);
    }

}
