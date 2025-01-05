package com.cwc.assessment.rafifuzz.service.impl;

import com.cwc.assessment.rafifuzz.dto.request.IncidentRequest;
import com.cwc.assessment.rafifuzz.models.Incident;
import com.cwc.assessment.rafifuzz.models.enums.Status;
import com.cwc.assessment.rafifuzz.repository.IncidentRepository;
import com.cwc.assessment.rafifuzz.service.IncidentService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepository incidentRepository;

  
    public IncidentServiceImpl(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }
    
	@Override
	public Incident createIncident(IncidentRequest incidentRequest, Long userId, String reporterName) {
		incidentRequest.setUserId(userId);
		  String loggedInUsername = getLoggedInUsername();
		//Reporter Name is logged user
		 incidentRequest.setReporterName(loggedInUsername != null ? loggedInUsername : reporterName);
		 Incident incident = mapToIncidentRequest(incidentRequest);
		 return this.incidentRepository.save(incident);
	}
	
	
	private String getLoggedInUsername() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication != null && authentication.isAuthenticated()) {
	        return authentication.getName();
	    }
	    return null; 
	}



    private Incident mapToIncidentRequest(IncidentRequest incidentRequest) {
        return Incident.builder()
                .incidentId(incidentRequest.getIncidentId())
                .details(incidentRequest.getDetails())
                .reporterName(incidentRequest.getReporterName())
                .reportedAt(incidentRequest.getReportedAt())
                .status(incidentRequest.getStatus())
                .priority(incidentRequest.getPriority())
                .type(incidentRequest.getType())
                .userId(incidentRequest.getUserId())
                .build();
    }

    @Override
    public List<Incident> getUserIncidents(Long userId) {
        List<Incident> incidentList = this.incidentRepository.findByUserId(userId);
        return incidentList;
    }

    public Optional<Incident> findIncidentById(String incidentId) {
        return incidentRepository.findByIncidentId(incidentId);
    }
    
    
    @Override
    public Incident updateIncident(String incidentId, IncidentRequest incidentRequest, Long userId) {
        Incident existingIncident = incidentRepository.findByIncidentId(incidentId)
            .orElseThrow(() -> new RuntimeException("Incident not found"));

        if (!existingIncident.getUserId().equals(userId)) {
            throw new RuntimeException("You do not have permission to edit this incident");
        }

        if (existingIncident.getStatus() == Status.CLOSED) {
            throw new RuntimeException("Cannot edit a closed incident");
        }

        existingIncident.setDetails(incidentRequest.getDetails());
        existingIncident.setPriority(incidentRequest.getPriority());
        existingIncident.setStatus(incidentRequest.getStatus());

        return incidentRepository.save(existingIncident);
    }



}
