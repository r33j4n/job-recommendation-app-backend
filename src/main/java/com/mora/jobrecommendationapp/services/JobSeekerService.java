package com.mora.jobrecommendationapp.services;

import com.mora.jobrecommendationapp.DTO.*;
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
                .isCvUploaded(false)
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

    public UpdateJobSeekerResponseDTO updateJobSeeker(long id, UpdateJobSeekerRequestDTO updateJobSeekerRequest) {

        JobSeeker jobSeeker = jobSeekerRepository.findById(id).get();;
        if (jobSeeker != null) {
            jobSeeker.setFirstName(updateJobSeekerRequest.getFirstName());
            jobSeeker.setLastName(updateJobSeekerRequest.getLastName());
            jobSeeker.setPhoneNumber(updateJobSeekerRequest.getPhoneNumber());
            jobSeeker.setAddress(updateJobSeekerRequest.getAddress());
            jobSeeker.setDob(updateJobSeekerRequest.getDob());
            jobSeeker.setGender(updateJobSeekerRequest.getGender());
            jobSeekerRepository.save(jobSeeker);
            UpdateJobSeekerResponseDTO updateJobSeekerResponseDTO = UpdateJobSeekerResponseDTO.builder().
                    message("Job Seeker Updated Successfully for the user name "+jobSeeker.getUserName())
                    .build();
            return updateJobSeekerResponseDTO;
        }

        UpdateJobSeekerResponseDTO updateJobSeekerResponseDTO = UpdateJobSeekerResponseDTO.builder().
                message("Job Seeker Not Updated ")
                .build();
        return updateJobSeekerResponseDTO;    }


    // Update your service method to fetch the JobSeeker entity eagerly
    public GetJobSeekerByIDResponseDTO getJobSeekerById(Long id) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(id).get();
        GetJobSeekerByIDResponseDTO getJobSeekerByIDResponseDTO = GetJobSeekerByIDResponseDTO.builder()
                .firstName(jobSeeker.getFirstName())
                .lastName(jobSeeker.getLastName())
                .email(jobSeeker.getEmail())
                .phoneNumber(jobSeeker.getPhoneNumber())
                .address(jobSeeker.getAddress())
                .dob(jobSeeker.getDob())
                .gender(jobSeeker.getGender())
                .registeredDate(jobSeeker.getRegisteredDate())
                .userName(jobSeeker.getUserName())
                .education(jobSeeker.getEducation())
                .experience(jobSeeker.getExperience())
                .skills(jobSeeker.getSkills())
                .isCvUploaded(jobSeeker.getIsCvUploaded())
                .build();
        return getJobSeekerByIDResponseDTO;
    }

    public DeleteJobSeekerResponseDTO deleteJobSeeker(long id) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(id).get();
        if (jobSeeker != null) {
            jobSeekerRepository.delete(jobSeeker);
            DeleteJobSeekerResponseDTO deleteJobSeekerResponseDTO = DeleteJobSeekerResponseDTO.builder().
                    message("Job Seeker Deleted Successfully ")
                    .build();
            return deleteJobSeekerResponseDTO;
        }
        DeleteJobSeekerResponseDTO deleteJobSeekerResponseDTO = DeleteJobSeekerResponseDTO.builder().
                message("Job Seeker Not Deleted")
                .build();
        return deleteJobSeekerResponseDTO;
    }

    public UploadCVResponseDTO updateCV(long id) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(id).get();
        if (jobSeeker != null) {
            jobSeeker.setIsCvUploaded(true);
            jobSeekerRepository.save(jobSeeker);
            UploadCVResponseDTO uploadCVResponseDTO = UploadCVResponseDTO.builder().
                    message("CV Updated Successfully")
                    .build();
            return uploadCVResponseDTO;
        }
        UploadCVResponseDTO uploadCVResponseDTO = UploadCVResponseDTO.builder().
                message("CV Not Updated")
                .build();
        return uploadCVResponseDTO;
    }

    public UpdateJobSeekerSkillsResponseDTO updateSkills(long id, UpdateJobSeekerSkillsRequestDTO updateJobSeekerSkillsRequest) {

        JobSeeker jobSeeker = jobSeekerRepository.findById(id).get();
        if (jobSeeker != null) {
            jobSeeker.setSkills(updateJobSeekerSkillsRequest.getSkills());
            jobSeeker.setExperience(updateJobSeekerSkillsRequest.getExperience());
            jobSeeker.setEducation(updateJobSeekerSkillsRequest.getEducation());
            jobSeekerRepository.save(jobSeeker);
            UpdateJobSeekerSkillsResponseDTO updateJobSeekerSkillsResponseDTO = UpdateJobSeekerSkillsResponseDTO.builder().
                    message("Skills Updated Successfully")
                    .build();
            return updateJobSeekerSkillsResponseDTO;
        }
        UpdateJobSeekerSkillsResponseDTO updateJobSeekerSkillsResponseDTO = UpdateJobSeekerSkillsResponseDTO.builder().
                message("Skills Not Updated")
                .build();
        return updateJobSeekerSkillsResponseDTO;
    }

    public GetJobSeekerSkillsResponseDTO getJobSeekerSkillsById(Long id) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(id).get();
        GetJobSeekerSkillsResponseDTO getJobSeekerSkillsResponseDTO = GetJobSeekerSkillsResponseDTO.builder()
                .id(jobSeeker.getJobSeekerId())
                .skills(jobSeeker.getSkills())
                .build();
        return getJobSeekerSkillsResponseDTO;
    }
}
