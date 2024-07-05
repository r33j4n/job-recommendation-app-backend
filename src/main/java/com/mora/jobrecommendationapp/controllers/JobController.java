package com.mora.jobrecommendationapp.controllers;

import com.mora.jobrecommendationapp.DTO.CreateJobRequestDTO;
import com.mora.jobrecommendationapp.DTO.CreateJobResponseDTO;
import com.mora.jobrecommendationapp.DTO.JobDTO;
import com.mora.jobrecommendationapp.entities.Job;
import com.mora.jobrecommendationapp.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private JobService jobService;

    @PostMapping("/create")
    public ResponseEntity<CreateJobResponseDTO> createJob(@RequestBody CreateJobRequestDTO createJobRequestDTO) {
        return ResponseEntity.ok(jobService.createJob(createJobRequestDTO));
    }

//    @PostMapping("/createJob")
//    public Job createJob(@RequestBody Job job) {
//        return jobService.createJob(job);
//    }

//    @GetMapping("/getAllJob")
//    public ResponseEntity<List<JobDTO>> getAllJobs() {
//        List<JobDTO> jobs = jobService.getAllJobs();
//        return ResponseEntity.ok(jobs);
//    }

}
