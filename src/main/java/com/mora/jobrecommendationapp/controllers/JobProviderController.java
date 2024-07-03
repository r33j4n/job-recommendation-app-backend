package com.mora.jobrecommendationapp.controllers;

import com.mora.jobrecommendationapp.DTO.CreateJobProviderRequestDTO;
import com.mora.jobrecommendationapp.DTO.CreateJobProviderResponseDTO;
import com.mora.jobrecommendationapp.services.JobProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobprovider")
public class JobProviderController {
    @Autowired
    private JobProviderService jobProviderService;

    @PostMapping("/create")
    public ResponseEntity<CreateJobProviderResponseDTO> createJobProvider(@RequestBody CreateJobProviderRequestDTO jobProviderRequest) {
        return ResponseEntity.ok(jobProviderService.createJobProvider(jobProviderRequest));
    }
}
