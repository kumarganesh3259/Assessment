package com.cwc.assessment.rafifuzz.models;

import com.cwc.assessment.rafifuzz.models.enums.Priority;
import com.cwc.assessment.rafifuzz.models.enums.Status;
import com.cwc.assessment.rafifuzz.models.enums.Type;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "incident_tbl")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String incidentId;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String details;
    private String reporterName; 
    private LocalDateTime reportedAt;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;
    private Long userId;

    @PrePersist
    public void prePersist() {
        this.reportedAt = LocalDateTime.now();
        this.incidentId = "RMG" + (int) (Math.random() * 100000) + LocalDateTime.now().getYear();
    }
}
