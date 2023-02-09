package com.example.makalu.dto.participant;

import com.example.makalu.domain.enums.ProjectRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public record ParticipantPayload(
        @NotBlank @Email
        String email,
        @NotNull
        ProjectRole role
) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipantPayload that = (ParticipantPayload) o;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

}
