package com.mora.jobrecommendationapp.controllers;

import com.mora.jobrecommendationapp.DTO.CreateJobRequestDTO;
import com.mora.jobrecommendationapp.DTO.CreateJobResponseDTO;
import com.mora.jobrecommendationapp.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping("/create")
    public ResponseEntity<CreateJobResponseDTO> createJob(@RequestBody CreateJobRequestDTO createJobRequestDTO) {
        return ResponseEntity.ok(jobService.createJob(createJobRequestDTO));
    }

}
