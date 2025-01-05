package com.cwc.assessment.rafifuzz.service;

import com.cwc.assessment.rafifuzz.dto.request.IncidentRequest;
import com.cwc.assessment.rafifuzz.dto.response.IncidentResponse;
import com.cwc.assessment.rafifuzz.models.Incident;

import java.util.List;
import java.util.Optional;

public interface IncidentService {
    Incident createIncident(IncidentRequest incidentRequest, Long userId, String reporterName);
    List<Incident> getUserIncidents(Long userId);
    Optional<Incident> findIncidentById(String incidentId);
    Incident updateIncident(String incidentId, IncidentRequest incidentRequest, Long userId);
}
