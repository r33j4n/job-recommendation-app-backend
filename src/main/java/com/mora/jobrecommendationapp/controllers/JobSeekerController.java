package com.mora.jobrecommendationapp.controllers;

import com.mora.jobrecommendationapp.DTO.*;
import com.mora.jobrecommendationapp.services.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
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

    @PutMapping ("/update/{id}")
    public ResponseEntity<UpdateJobSeekerResponseDTO> updateJobSeeker(@PathVariable("id") long id,@RequestBody UpdateJobSeekerRequestDTO updateJobSeekerRequest) {
        return ResponseEntity.ok(jobSeekerService.updateJobSeeker(id,updateJobSeekerRequest));
    }

    @GetMapping("/get/{id}")
    public GetJobSeekerByIDResponseDTO getJobSeekerById(@PathVariable Long id) {
        return jobSeekerService.getJobSeekerById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DeleteJobSeekerResponseDTO> deleteJobSeeker(@PathVariable("id") long id) {
        return ResponseEntity.ok(jobSeekerService.deleteJobSeeker(id));
    }

    @PutMapping("/update_cv/{id}")
    public ResponseEntity<UploadCVResponseDTO> updateCV(@PathVariable("id") long id) {
        return ResponseEntity.ok(jobSeekerService.updateCV(id));
    }

    @PutMapping("/update_skills/{id}")
    public ResponseEntity<UpdateJobSeekerSkillsResponseDTO> updateSkills(@PathVariable("id") long id,@RequestBody UpdateJobSeekerSkillsRequestDTO updateJobSeekerSkillsRequest) {
        return ResponseEntity.ok(jobSeekerService.updateSkills(id,updateJobSeekerSkillsRequest));
    }

}
