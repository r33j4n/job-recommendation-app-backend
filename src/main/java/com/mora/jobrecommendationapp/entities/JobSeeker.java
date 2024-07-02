package com.mora.jobrecommendationapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "jobseeker")
public class JobSeeker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobSeekerId;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String userName;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String phoneNumber;
    @Column
    private String address;
    @Column
    private String dob;
    @Column
    private String gender;
    @Column
    private String registeredDate;
    @Column
    private String education;
    @Column
    private String experience;
    @Column
    private String skills;
    @Column
    private String appliedJobs;

}
