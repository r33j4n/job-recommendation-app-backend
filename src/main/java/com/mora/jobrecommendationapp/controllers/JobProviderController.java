package com.mora.jobrecommendationapp.controllers;

import com.mora.jobrecommendationapp.DTO.*;
import com.mora.jobrecommendationapp.entities.JobProvider;
import com.mora.jobrecommendationapp.services.JobProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobprovider")
public class JobProviderController {
    @Autowired
    private JobProviderService jobProviderService;

    @PostMapping("/create")
    public ResponseEntity<CreateJobProviderResponseDTO> createJobProvider(@RequestBody CreateJobProviderRequestDTO jobProviderRequest) {
        return ResponseEntity.ok(jobProviderService.createJobProvider(jobProviderRequest));
    }

    @GetMapping("/getAllJobProvider")
    public List<JobProvider> getAllJobProvider() {
        return jobProviderService.getAllJobProvider();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginJobProviderRequestDTO loginJobProviderRequestDTO) {
        return ResponseEntity.ok(jobProviderService.performlogin(loginJobProviderRequestDTO));
    }
}
