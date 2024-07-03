package com.mora.jobrecommendationapp.repositories;

import com.mora.jobrecommendationapp.entities.JobProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobProviderRepository extends JpaRepository<JobProvider, Long> {
}
