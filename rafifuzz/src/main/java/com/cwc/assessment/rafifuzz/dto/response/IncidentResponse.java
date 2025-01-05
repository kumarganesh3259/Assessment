package com.cwc.assessment.rafifuzz.dto.response;

import com.cwc.assessment.rafifuzz.models.enums.Priority;
import com.cwc.assessment.rafifuzz.models.enums.Status;
import com.cwc.assessment.rafifuzz.models.enums.Type;
import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncidentResponse {
    private Long id;
    private String incidentId;
    private Type type;
    private String details;
    private LocalDateTime reportedAt;
    private Priority priority;
    private String reporterName; 
    private Status status;
    private Long userId;
}
