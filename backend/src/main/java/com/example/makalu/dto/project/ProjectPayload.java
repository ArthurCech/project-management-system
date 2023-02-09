package com.example.makalu.dto.project;

import javax.validation.constraints.NotBlank;

public record ProjectPayload(
        @NotBlank String name,
        String description
) {
}
