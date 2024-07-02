package com.mora.jobrecommendationapp.services;

import com.mora.jobrecommendationapp.DTO.CreateJobSeekerRequestDTO;
import com.mora.jobrecommendationapp.DTO.CreateJobSeekerResponseDTO;
import com.mora.jobrecommendationapp.entities.JobSeeker;
import com.mora.jobrecommendationapp.repositories.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class JobSeekerService {
    @Autowired
    JobSeekerRepository jobSeekerRepository;

    public CreateJobSeekerResponseDTO createJobSeeker(CreateJobSeekerRequestDTO jobSeekerRequest) {

        JobSeeker jobSeeker = JobSeeker.builder()
                .firstName(jobSeekerRequest.getFirstName())
                .lastName(jobSeekerRequest.getLastName())
                .userName(jobSeekerRequest.getUserName())
                .email(jobSeekerRequest.getEmail())
                .password(jobSeekerRequest.getPassword())
                .phoneNumber(jobSeekerRequest.getPhoneNumber())
                .address(jobSeekerRequest.getAddress())
                .dob(jobSeekerRequest.getDob())
                .gender(jobSeekerRequest.getGender())
                .registeredDate(jobSeekerRequest.getRegisteredDate())
                .build();
        jobSeekerRepository.save(jobSeeker);
        CreateJobSeekerResponseDTO createJobSeekerResponseDTO = CreateJobSeekerResponseDTO.builder().
                message("Job Seeker Created Successfully for the user name "+jobSeekerRequest.getUserName())
                .build();

        return createJobSeekerResponseDTO;
    }
}
