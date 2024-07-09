package com.mora.jobrecommendationapp.repositories;

import com.mora.jobrecommendationapp.entities.Application;
import com.mora.jobrecommendationapp.entities.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("SELECT COUNT(a) FROM Application a WHERE a.job.jobId = :jobId")
    Long countByJobId(@Param("jobId") Long jobId);

}
