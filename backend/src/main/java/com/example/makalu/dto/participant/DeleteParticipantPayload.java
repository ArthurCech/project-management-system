package com.example.makalu.dto.participant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record DeleteParticipantPayload(
        @NotNull @Positive
        Long id
) {
}
