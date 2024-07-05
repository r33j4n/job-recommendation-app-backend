package com.mora.jobrecommendationapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobId;

    @Column
    private String jobTitle;

    @Column
    private String jobDescription;

    @Column
    private String jobLocation;

    @Column
    private String jobExperience;

    @Column
    private String qualifiedEducation;

    @Column
    private String jobSkills;

    @Column
    private Date jobPostedDate;

    @Column
    private Boolean isHired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_provider_id",nullable = false)
    @JsonIgnoreProperties("jobs")
    private JobProvider jobProvider;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications = new ArrayList<>();

}

