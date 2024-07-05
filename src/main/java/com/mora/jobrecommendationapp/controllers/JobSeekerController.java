package com.mora.jobrecommendationapp.controllers;

import com.mora.jobrecommendationapp.DTO.CreateJobSeekerRequestDTO;
import com.mora.jobrecommendationapp.DTO.CreateJobSeekerResponseDTO;
import com.mora.jobrecommendationapp.DTO.LoginJobSeekerRequestDTO;
import com.mora.jobrecommendationapp.DTO.LoginResponse;
import com.mora.jobrecommendationapp.services.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobseeker")

public class JobSeekerController {
    @Autowired
    private JobSeekerService jobSeekerService;
    @PostMapping("/create")
    public ResponseEntity<CreateJobSeekerResponseDTO> createJobSeeker(@RequestBody CreateJobSeekerRequestDTO jobSeekerRequest) {
        return ResponseEntity.ok(jobSeekerService.createJobSeeker(jobSeekerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginJobSeekerRequestDTO loginJobSeekerRequestDTO) {
        return ResponseEntity.ok(jobSeekerService.performlogin(loginJobSeekerRequestDTO));
    }


}
