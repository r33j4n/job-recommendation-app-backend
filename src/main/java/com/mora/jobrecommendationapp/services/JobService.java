package com.mora.jobrecommendationapp.services;

import com.mora.jobrecommendationapp.DTO.*;
import com.mora.jobrecommendationapp.entities.Job;
import com.mora.jobrecommendationapp.entities.JobProvider;
import com.mora.jobrecommendationapp.repositories.JobProviderRepository;
import com.mora.jobrecommendationapp.repositories.JobRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobService {
    @Autowired
    JobRepository jobRepository;
    @Autowired
    JobProviderRepository jobProviderRepository;

    public CreateJobResponseDTO createJob( CreateJobRequestDTO createJobRequestDTO) {
        Optional<JobProvider> jobProvider = Optional.of(new JobProvider());
        jobProvider= jobProviderRepository.findById(createJobRequestDTO.getJobProviderId());
        Job job = Job.builder()
                .jobTitle(createJobRequestDTO.getJobTitle())
                .jobDescription(createJobRequestDTO.getJobDescription())
                .jobLocation(createJobRequestDTO.getJobLocation())
                .jobExperience(createJobRequestDTO.getJobExperience())
                .qualifiedEducation(createJobRequestDTO.getQualifiedEducation())
                .jobSkills(createJobRequestDTO.getJobSkills())
                .jobPostedDate(createJobRequestDTO.getJobPostedDate())
                .jobProvider(jobProvider.get())
                .build();
        jobRepository.save(job);
        CreateJobResponseDTO createJobResponseDTO = CreateJobResponseDTO.builder()
                .message(job.getJobTitle()+" Job Created Successfully with "+ job.getJobSkills())
                .build();
        return createJobResponseDTO;
    }

    public UpdateJobResponseDTO updateJob(long id, UpdateJobRequestDTO updateJobRequestDTO) throws ChangeSetPersister.NotFoundException {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        job.setJobTitle(updateJobRequestDTO.getJobTitle());
        job.setJobDescription(updateJobRequestDTO.getJobDescription());
        job.setJobLocation(updateJobRequestDTO.getJobLocation());
        job.setJobExperience(updateJobRequestDTO.getJobExperience());
        job.setQualifiedEducation(updateJobRequestDTO.getQualifiedEducation());
        job.setJobSkills(updateJobRequestDTO.getJobSkills());
        jobRepository.save(job);
        UpdateJobResponseDTO updateJobResponseDTO = UpdateJobResponseDTO.builder().
                message("Job Seeker Updated Successfully for the user name ")
                .build();
        return updateJobResponseDTO;
    }

    @Transactional
    public List<JobDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private JobDTO mapToDTO(Job job) {
        return JobDTO.builder()
                .jobId(job.getJobId())
                .jobTitle(job.getJobTitle())
                .jobDescription(job.getJobDescription())
                .jobLocation(job.getJobLocation())
                .jobExperience(job.getJobExperience())
                .qualifiedEducation(job.getQualifiedEducation())
                .jobSkills(job.getJobSkills())
                .jobPostedDate(job.getJobPostedDate())
                .isHired(job.getIsHired())
                .build();
    }

    public JobDTO getJobById(long id) {
        Optional<Job> job = jobRepository.findById(id);
        return mapToDTO(job.orElse(null));
    }

//    public Job createJob(Job job) {
//        // Check if jobProviderId is provided
////        if (job.getJobProviderId() != null) {
////            JobProvider jobProvider = jobProviderRepository.findById(job.getJobProviderId())
////                    .orElseThrow(() -> new RuntimeException("JobProvider not found"));
////            job.setJobProvider(jobProvider);
////        }
//        job.setJobPostedDate(new Date());
//        return jobRepository.save(job);
//    }
}
