package com.example.makalu.dto.project;

import com.example.makalu.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.Instant;

@JsonPropertyOrder({"id", "name", "description", "status", "progress", "createdAt", "updatedAt"})
public record ProjectResponse(
        Long id,
        String name,
        String description,
        Status status,
        Integer progress,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt
) {
}
