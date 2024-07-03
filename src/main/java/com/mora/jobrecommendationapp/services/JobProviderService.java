package com.mora.jobrecommendationapp.services;

import com.mora.jobrecommendationapp.DTO.CreateJobProviderRequestDTO;
import com.mora.jobrecommendationapp.DTO.CreateJobProviderResponseDTO;
import com.mora.jobrecommendationapp.entities.JobProvider;
import com.mora.jobrecommendationapp.repositories.JobProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobProviderService {
    @Autowired
    JobProviderRepository jobProviderRepository;

    public CreateJobProviderResponseDTO createJobProvider(CreateJobProviderRequestDTO jobProviderRequest) {

        JobProvider jobProvider = JobProvider.builder()
                .companyName(jobProviderRequest.getCompanyName())
                .industry(jobProviderRequest.getIndustry())
                .email(jobProviderRequest.getEmail())
                .userName(jobProviderRequest.getUserName())
                .password(jobProviderRequest.getPassword())
                .phoneNumber(jobProviderRequest.getPhoneNumber())
                .address(jobProviderRequest.getAddress())
                .registeredDate(jobProviderRequest.getRegisteredDate())
                .build();
        jobProviderRepository.save(jobProvider);
        CreateJobProviderResponseDTO createJobProviderResponseDTO = CreateJobProviderResponseDTO.builder().
                message("Job Provider Created Successfully for the company "+jobProviderRequest.getCompanyName())
                .build();
        return createJobProviderResponseDTO;
    }
}
