package com.jobtracker.job_tracker.service;

import com.jobtracker.job_tracker.model.ApplicationStatus;
import com.jobtracker.job_tracker.model.JobApplication;
import com.jobtracker.job_tracker.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jobtracker.job_tracker.exception.ResourceNotFoundException;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public JobApplication addApplication(JobApplication application) {
        if (application.getAppliedDate() == null) {
            application.setAppliedDate(LocalDate.now());
        }
        if (application.getStatus() == null) {
            application.setStatus(ApplicationStatus.APPLIED);
        }
        return repository.save(application);
    }

    public List<JobApplication> getAllApplications() {
        return repository.findAll();
    }

    public JobApplication getApplicationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Application not found with id: " + id));
    }

    public List<JobApplication> getByStatus(ApplicationStatus status) {
        return repository.findByStatus(status);
    }

    @Transactional
    public JobApplication updateApplication(Long id, JobApplication updatedData) {
        JobApplication existing = getApplicationById(id);
        existing.setCompanyName(updatedData.getCompanyName());
        existing.setJobRole(updatedData.getJobRole());
        existing.setStatus(updatedData.getStatus());
        existing.setNotes(updatedData.getNotes());
        existing.setLocation(updatedData.getLocation());
        existing.setSalaryRange(updatedData.getSalaryRange());
        return repository.save(existing);
    }

    @Transactional
    public JobApplication updateStatus(Long id, ApplicationStatus newStatus) {
        JobApplication application = getApplicationById(id);
        application.setStatus(newStatus);
        return repository.save(application);
    }

    @Transactional
    public void deleteApplication(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Application not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public List<JobApplication> searchByCompany(String keyword) {
        return repository.findByCompanyNameContainingIgnoreCase(keyword);
    }

    public Map<String, Long> getDashboardStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("APPLIED",   repository.countByStatus(ApplicationStatus.APPLIED));
        stats.put("INTERVIEW", repository.countByStatus(ApplicationStatus.INTERVIEW));
        stats.put("OFFERED",   repository.countByStatus(ApplicationStatus.OFFERED));
        stats.put("REJECTED",  repository.countByStatus(ApplicationStatus.REJECTED));
        stats.put("TOTAL",     repository.count());
        return stats;
    }
}