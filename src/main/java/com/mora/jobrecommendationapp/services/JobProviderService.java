package com.mora.jobrecommendationapp.services;

import com.mora.jobrecommendationapp.DTO.*;
import com.mora.jobrecommendationapp.JwtAuthenticationConfig.JWTAuthentication;
import com.mora.jobrecommendationapp.entities.Job;
import com.mora.jobrecommendationapp.entities.JobProvider;
import com.mora.jobrecommendationapp.entities.JobSeeker;
import com.mora.jobrecommendationapp.repositories.JobProviderRepository;
import com.mora.jobrecommendationapp.repositories.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobProviderService {
    @Autowired
    JobProviderRepository jobProviderRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private JWTAuthentication jwtA;

    @Autowired
    public JobProviderService(JobProviderRepository jobProviderRepository , BCryptPasswordEncoder bCryptPasswordEncoder, JWTAuthentication jwtA) {
        this.jobProviderRepository = jobProviderRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtA = jwtA;
    }

    public CreateJobProviderResponseDTO createJobProvider(CreateJobProviderRequestDTO jobProviderRequest) {
        String encryptedPassword = bCryptPasswordEncoder.encode(jobProviderRequest.getPassword());
        JobProvider jobProvider1 = jobProviderRepository.findByUserName(jobProviderRequest.getUserName());
        if (jobProvider1 == null) {
            JobProvider jobProvider = JobProvider.builder()
                    .companyName(jobProviderRequest.getCompanyName())
                    .industry(jobProviderRequest.getIndustry())
                    .email(jobProviderRequest.getEmail())
                    .userName(jobProviderRequest.getUserName())
                    .password(encryptedPassword)
                    .phoneNumber(jobProviderRequest.getPhoneNumber())
                    .address(jobProviderRequest.getAddress())
                    .registeredDate(jobProviderRequest.getRegisteredDate())
                    .build();
            jobProviderRepository.save(jobProvider);
            CreateJobProviderResponseDTO createJobProviderResponseDTO = CreateJobProviderResponseDTO.builder().
                    message("Job Provider Created Successfully for the company "+jobProviderRequest.getCompanyName())
                    .isDuplicated(false)
                    .build();
            return createJobProviderResponseDTO;
        } else {
            CreateJobProviderResponseDTO createJobProviderResponseDTO = CreateJobProviderResponseDTO.builder().
                    message("Job Provider Already Exists for the company "+jobProviderRequest.getCompanyName())
                    .isDuplicated(true)
                    .build();
            return createJobProviderResponseDTO;
        }
    }

    public List<JobProvider> getAllJobProvider() {
        return jobProviderRepository.findAll();
    }

    public LoginResponse performlogin(LoginJobProviderRequestDTO loginJobProviderRequestDTO) {
        JobProvider jobProvider = jobProviderRepository.findByUserName(loginJobProviderRequestDTO.getUserName());
        String userName = loginJobProviderRequestDTO.getUserName();
        String password = loginJobProviderRequestDTO.getPassword();

        if (jobProvider == null) {
            return new LoginResponse("User not found", false,null);
        } else {
            boolean isPasswordMatched = bCryptPasswordEncoder.matches(password, jobProvider.getPassword());
            if (isPasswordMatched) {
                String token= jwtA.generateTokenForJobProvider(userName);
                return new LoginResponse("Login Successful", true,token);
            } else {
                return new LoginResponse("Incorrect Password", false,null);
            }
        }
    }

    public UpdateJobProviderResponseDTO updateJobProvider(long id, UpdateJobProviderRequestDTO updateJobProviderRequest) {

        JobProvider jobProvider = jobProviderRepository.findById(id).get();;
//        jobProvider = JobProvider.builder()
//                .companyName(updateJobProviderRequest.getCompanyName())
//                .industry(updateJobProviderRequest.getIndustry())
//                .email(updateJobProviderRequest.getEmail())
//                .phoneNumber(updateJobProviderRequest.getPhoneNumber())
//                .address(updateJobProviderRequest.getAddress())
//
//                .build();
        jobProvider.setCompanyName(updateJobProviderRequest.getCompanyName());
        jobProvider.setIndustry(updateJobProviderRequest.getIndustry());
        jobProvider.setEmail(updateJobProviderRequest.getEmail());
        jobProvider.setPhoneNumber(updateJobProviderRequest.getPhoneNumber());
        jobProvider.setAddress(updateJobProviderRequest.getAddress());
        jobProviderRepository.save(jobProvider);
        UpdateJobProviderResponseDTO updateJobProviderResponseDTO = UpdateJobProviderResponseDTO.builder().
                message("Job Provider Updated Successfully")
                .build();
        return updateJobProviderResponseDTO;
    }

    public JobProvider getJobProviderById(Long jobProviderId) {
        return jobProviderRepository.findById(jobProviderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid job provider ID: " + jobProviderId));
    }

    public List<Job> getJobsByJobProviderId(Long jobProviderId) {
        JobProvider jobProvider = getJobProviderById(jobProviderId);
        return jobProvider.getJobs();
    }

    public Long getJobCountByJobProviderId(Long jobProviderId) {
        JobProvider jobProvider = getJobProviderById(jobProviderId);
        return (long) jobProvider.getJobs().size();
    }

//    public void deleteJobProviderById(Long jobProviderId) {
//        if (jobProviderRepository.existsById(jobProviderId)) {
//            jobProviderRepository.deleteById(jobProviderId);
//        } else {
//            throw new IllegalArgumentException("Invalid job provider ID: " + jobProviderId);
//        }
//    }

    public DeleteJobSeekerResponseDTO deleteJobProviderById(Long jobProviderId) {
        if (jobProviderRepository.existsById(jobProviderId)) {
            jobProviderRepository.deleteById(jobProviderId);
            DeleteJobSeekerResponseDTO deleteJobSeekerResponseDTO = DeleteJobSeekerResponseDTO.builder().
                    message("Job Seeker Deleted Successfully ")
                    .build();
            return deleteJobSeekerResponseDTO;
        } else {
            DeleteJobSeekerResponseDTO deleteJobSeekerResponseDTO = DeleteJobSeekerResponseDTO.builder().
                    message("Job Seeker Not Deleted")
                    .build();
            return deleteJobSeekerResponseDTO;
        }
    }

    public GetJobProviderByIDResponseDTO getJobProviderById1(Long id) {
        JobProvider jobProvider = jobProviderRepository.findById(id).get();
        GetJobProviderByIDResponseDTO getJobProviderByIDResponseDTO = GetJobProviderByIDResponseDTO.builder()
                .companyName(jobProvider.getCompanyName())
                .industry(jobProvider.getIndustry())
                .email(jobProvider.getEmail())
                .phoneNumber(jobProvider.getPhoneNumber())
                .address(jobProvider.getAddress())
                .userName(jobProvider.getUserName())
                .registeredDate(jobProvider.getRegisteredDate())
                .build();
        return getJobProviderByIDResponseDTO;
    }
}
