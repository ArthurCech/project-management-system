package com.example.makalu.dto.sprint;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public record SprintPayload(
        @NotBlank String name,
        String description,
        @FutureOrPresent @JsonProperty("start_date") LocalDate startDate,
        @FutureOrPresent LocalDate deadline) {
}
