package com.example.makalu.dto.participant;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public record ParticipantsPayload(
        @NotNull
        @Valid
        @Size(min = 1, max = 20)
        @UniqueElements
        List<ParticipantPayload> participants
) {
}
