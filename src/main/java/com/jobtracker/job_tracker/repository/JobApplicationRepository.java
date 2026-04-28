package com.jobtracker.job_tracker.repository;

import com.jobtracker.job_tracker.model.ApplicationStatus;
import com.jobtracker.job_tracker.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByStatus(ApplicationStatus status);

    List<JobApplication> findByCompanyNameContainingIgnoreCase(String companyName);

    long countByStatus(ApplicationStatus status);
}