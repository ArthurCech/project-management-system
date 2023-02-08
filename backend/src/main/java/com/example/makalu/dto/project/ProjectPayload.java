package com.example.makalu.dto.project;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public record ProjectPayload(
        @NotBlank String name,
        String description,
        @FutureOrPresent @JsonProperty("start_date") LocalDate startDate,
        @FutureOrPresent LocalDate deadline
) {
}
