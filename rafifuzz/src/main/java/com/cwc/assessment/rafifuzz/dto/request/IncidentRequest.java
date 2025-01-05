package com.cwc.assessment.rafifuzz.dto.request;

import com.cwc.assessment.rafifuzz.models.enums.Priority;
import com.cwc.assessment.rafifuzz.models.enums.Status;
import com.cwc.assessment.rafifuzz.models.enums.Type;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncidentRequest {
    private Long id;
    private String incidentId;
    private Type type;
    private String details;
    private String reporterName; 
    private LocalDateTime reportedAt;
    private Priority priority;
    private Status status;
    private Long userId;
}
