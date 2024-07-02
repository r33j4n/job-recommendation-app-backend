package com.mora.jobrecommendationapp.entities;

import jakarta.persistence.*;

@Entity
public class JobProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobProviderId;
    @Column
    private String firstName;

}

