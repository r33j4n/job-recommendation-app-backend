package com.mora.jobrecommendationapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Boolean isCvUploaded;
    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Prevents serialization of the jobs list when JobProvider is serialized
    private List<Application> applications= new ArrayList<>();

}
