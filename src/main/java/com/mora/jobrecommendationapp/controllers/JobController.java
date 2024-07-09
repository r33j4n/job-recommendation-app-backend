package com.mora.jobrecommendationapp.controllers;

import com.mora.jobrecommendationapp.DTO.*;
import com.mora.jobrecommendationapp.entities.Job;
import com.mora.jobrecommendationapp.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateJobResponseDTO> updateJob(@PathVariable("id") long id, @RequestBody UpdateJobRequestDTO updateJobRequestDTO) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(jobService.updateJob(id, updateJobRequestDTO));
    }

//    @PostMapping("/createJob")
//    public Job createJob(@RequestBody Job job) {
//        return jobService.createJob(job);
//    }

    @GetMapping("/getAllJob")
    public ResponseEntity<List<JobDTO>> getAllJobs() {
        List<JobDTO> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/getJobById/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable("id") long id) {
        JobDTO job = jobService.getJobById(id);
        return ResponseEntity.ok(job);
    }

}
