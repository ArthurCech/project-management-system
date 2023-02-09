package com.example.makalu.dto.sprint;

import com.example.makalu.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.Instant;
import java.time.LocalDate;

@JsonPropertyOrder({"id", "name", "description", "startDate", "deadline", "status",
        "progress", "createdAt", "updatedAt"})
public record SprintResponse(
        Long id,
        String name,
        String description,
        @JsonProperty("start_date") LocalDate startDate,
        LocalDate deadline,
        Status status,
        Integer progress,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt
) {
}
