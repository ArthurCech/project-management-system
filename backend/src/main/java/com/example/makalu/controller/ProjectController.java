package com.example.makalu.controller;

import com.example.makalu.dto.participant.DeleteParticipantPayload;
import com.example.makalu.dto.participant.ParticipantsPayload;
import com.example.makalu.dto.participant.ParticipantsResponse;
import com.example.makalu.dto.project.ProjectPayload;
import com.example.makalu.dto.project.ProjectResponse;
import com.example.makalu.dto.project.StatusPayload;
import com.example.makalu.dto.sprint.SprintPayload;
import com.example.makalu.dto.sprint.SprintResponse;
import com.example.makalu.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@RequestBody @Valid ProjectPayload payload) {
        ProjectResponse response = projectService.createProject(payload);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> update(@PathVariable Long id,
                                                  @RequestBody @Valid ProjectPayload payload) {
        ProjectResponse response = projectService.update(id, payload);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectResponse> changeStatus(@PathVariable Long id,
                                                        @RequestBody @Valid StatusPayload payload) {
        ProjectResponse response = projectService.changeStatus(id, payload);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/participants")
    public ResponseEntity<ParticipantsResponse> addParticipant(@PathVariable Long id,
                                                               @RequestBody @Valid ParticipantsPayload payload) {
        ParticipantsResponse response = projectService.addParticipant(id, payload);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/participants")
    public ResponseEntity<Void> removeParticipant(@PathVariable Long id,
                                                  @RequestBody @Valid DeleteParticipantPayload payload) {
        projectService.deleteParticipant(id, payload);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/sprints")
    public ResponseEntity<SprintResponse> addSprintToProject(@PathVariable Long id,
                                                             @RequestBody @Valid SprintPayload payload) {
        SprintResponse responseBody = projectService.addSprintToProject(id, payload);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(responseBody.id()).toUri();
        return ResponseEntity.created(uri).body(responseBody);
    }

}
