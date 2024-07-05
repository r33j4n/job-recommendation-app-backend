package com.mora.jobrecommendationapp.controllers;

import com.mora.jobrecommendationapp.DTO.CreateApplicationRequestDTO;
import com.mora.jobrecommendationapp.DTO.CreateApplicationResponseDTO;
import com.mora.jobrecommendationapp.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/create")
    public ResponseEntity<CreateApplicationResponseDTO> createApplication(@RequestBody CreateApplicationRequestDTO createApplicationRequestDTO) {
        return ResponseEntity.ok(applicationService.createApplication(createApplicationRequestDTO));
    }

    @PostMapping("/count")
    public long getApplicationCountByJobId(@RequestParam Long jobId) {
        return applicationService.getApplicationCountByJobId(jobId);
    }
}
