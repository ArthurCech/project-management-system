package com.example.makalu.dto.participant;

import com.example.makalu.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
import java.util.List;

@JsonPropertyOrder({"projectId", "projectName", "projectDescription", "startDate",
        "deadline", "projectStatus", "participants"})
public record ParticipantsResponse(
        @JsonProperty("project_id") Long projectId,
        @JsonProperty("project_name") String projectName,
        @JsonProperty("project_description") String projectDescription,
        @JsonProperty("start_date") LocalDate startDate,
        LocalDate deadline,
        @JsonProperty("project_status") Status projectStatus,
        List<ParticipantResponse> participants
) {
}
