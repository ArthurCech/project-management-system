package com.example.makalu.dto.project;

import com.example.makalu.domain.enums.Status;

import javax.validation.constraints.NotNull;

public record StatusPayload(
        @NotNull Status status
) {
}
