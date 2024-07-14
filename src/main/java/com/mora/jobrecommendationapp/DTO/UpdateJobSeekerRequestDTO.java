package com.mora.jobrecommendationapp.DTO;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class UpdateJobSeekerRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String phoneNumber;
    private String address;
    private String dob;
    private String gender;
    private String registeredDate;
    private String education;
    private String experience;
    private String skills;
    private Boolean isCvUploaded;
}
