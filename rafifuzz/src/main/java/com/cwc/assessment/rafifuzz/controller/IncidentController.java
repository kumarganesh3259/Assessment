package com.cwc.assessment.rafifuzz.controller;

import com.cwc.assessment.rafifuzz.dto.request.IncidentRequest;
import com.cwc.assessment.rafifuzz.models.Incident;
import com.cwc.assessment.rafifuzz.service.IncidentService;

import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/incidents")
public class IncidentController {

    private final IncidentService incidentService;

  
    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }


    @SneakyThrows
    @PostMapping("/create")
    public ResponseEntity<Incident> createIncident(
            @RequestBody IncidentRequest incidentRequest,
            @RequestHeader("userId") Long userId,
            @RequestHeader("reporterName") String reporterName) {
        return ResponseEntity.ok(incidentService.createIncident(incidentRequest, userId, reporterName));
    }

    @SneakyThrows
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Incident>> getUserIncidents(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(incidentService.getUserIncidents(userId));
    }

    @SneakyThrows
    @GetMapping("/search")
    public ResponseEntity<Incident> searchIncident(@RequestParam("incidentId") String incidentId) {
        return incidentService.findIncidentById(incidentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @SneakyThrows
    @PutMapping("/{incidentId}")
    public ResponseEntity<Incident> updateIncident(
            @PathVariable String incidentId,
            @RequestBody IncidentRequest incidentRequest,
            @RequestHeader("userId") Long userId) {
        return ResponseEntity.ok(incidentService.updateIncident(incidentId, incidentRequest, userId));
    }
}//bol na ab whatsapp call kr lena

