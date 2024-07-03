package com.mora.jobrecommendationapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "jobprovider")
public class JobProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobProviderId;
    @Column
    private String companyName;
    @Column
    private String industry;
    @Column
    private String email;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private String phoneNumber;
    @Column
    private String address;
    @Column
    private Date registeredDate;
    @Column
    private String postedJobs;
}

