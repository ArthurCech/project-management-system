package com.example.makalu.dto.participant;

import com.example.makalu.domain.enums.ProjectRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "firstName", "lastName", "email", "role"})
public record ParticipantResponse(
        Long id,
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName,
        String email,
        ProjectRole role
) {
}
