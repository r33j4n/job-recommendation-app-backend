package com.mora.jobrecommendationapp.services;

import com.mora.jobrecommendationapp.DTO.CreateJobSeekerRequestDTO;
import com.mora.jobrecommendationapp.DTO.CreateJobSeekerResponseDTO;
import com.mora.jobrecommendationapp.DTO.LoginJobSeekerRequestDTO;
import com.mora.jobrecommendationapp.DTO.LoginResponse;
import com.mora.jobrecommendationapp.JwtAuthenticationConfig.JWTAuthentication;
import com.mora.jobrecommendationapp.entities.JobSeeker;
import com.mora.jobrecommendationapp.repositories.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class JobSeekerService {
    @Autowired
    JobSeekerRepository jobSeekerRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private JWTAuthentication jwtA;

    @Autowired
    public JobSeekerService(JobSeekerRepository jobSeekerRepository , BCryptPasswordEncoder bCryptPasswordEncoder, JWTAuthentication jwtA) {
        this.jobSeekerRepository = jobSeekerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtA = jwtA;
    }

    public CreateJobSeekerResponseDTO createJobSeeker(CreateJobSeekerRequestDTO jobSeekerRequest) {
        String encryptedPassword = bCryptPasswordEncoder.encode(jobSeekerRequest.getPassword());
        JobSeeker jobSeeker = JobSeeker.builder()
                .firstName(jobSeekerRequest.getFirstName())
                .lastName(jobSeekerRequest.getLastName())
                .userName(jobSeekerRequest.getUserName())
                .email(jobSeekerRequest.getEmail())
                .password(encryptedPassword)
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

    public LoginResponse performlogin(LoginJobSeekerRequestDTO loginJobSeekerRequestDTO) {
        JobSeeker jobSeeker = jobSeekerRepository.findByUserName(loginJobSeekerRequestDTO.getUserName());
        String userName = loginJobSeekerRequestDTO.getUserName();
        String password = loginJobSeekerRequestDTO.getPassword();

        if (jobSeeker == null) {
            return new LoginResponse("User not found", false,null);
        } else {
            boolean isPasswordMatched = bCryptPasswordEncoder.matches(password, jobSeeker.getPassword());
            if (isPasswordMatched) {
                String token= jwtA.generateToken(userName);
                return new LoginResponse("Login Successful", true,token);
            } else {
                return new LoginResponse("Incorrect Password", false,null);
            }
        }
    }
}
