package com.mora.jobrecommendationapp.services;

import com.mora.jobrecommendationapp.DTO.CreateJobRequestDTO;
import com.mora.jobrecommendationapp.DTO.CreateJobResponseDTO;
import com.mora.jobrecommendationapp.entities.Job;
import com.mora.jobrecommendationapp.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class JobService {
    @Autowired
    JobRepository jobRepository;

    public CreateJobResponseDTO createJob( CreateJobRequestDTO createJobRequestDTO) {

        Job job = Job.builder()
                .jobTitle(createJobRequestDTO.getJobTitle())
                .jobDescription(createJobRequestDTO.getJobDescription())
                .jobLocation(createJobRequestDTO.getJobLocation())
                .jobExperience(createJobRequestDTO.getJobExperience())
                .qualifiedEducation(createJobRequestDTO.getQualifiedEducation())
                .jobSkills(createJobRequestDTO.getJobSkills())
                .jobPostedDate(createJobRequestDTO.getJobPostedDate())
                .build();
        jobRepository.save(job);
        CreateJobResponseDTO createJobResponseDTO = CreateJobResponseDTO.builder()
                .message(job.getJobTitle()+" Job Created Successfully with "+ job.getJobSkills())
                .build();
        return createJobResponseDTO;
    }
}
