package com.mora.jobrecommendationapp.repositories;

import com.mora.jobrecommendationapp.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    @Query("SELECT COUNT(a) FROM Application a WHERE a.job.jobId = :jobId")
    long countByJobId(@Param("jobId") Long jobId);
}
