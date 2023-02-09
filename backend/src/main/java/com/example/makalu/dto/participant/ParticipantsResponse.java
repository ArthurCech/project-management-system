package com.example.makalu.dto.participant;

import com.example.makalu.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder({"projectId", "projectName", "projectDescription", "projectStatus", "participants"})
public record ParticipantsResponse(
        @JsonProperty("project_id") Long projectId,
        @JsonProperty("project_name") String projectName,
        @JsonProperty("project_description") String projectDescription,
        @JsonProperty("project_status") Status projectStatus,
        List<ParticipantResponse> participants
) {
}
