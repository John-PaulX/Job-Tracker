package com.jobtracker.job_tracker.controller;

import com.jobtracker.job_tracker.model.ApplicationStatus;
import com.jobtracker.job_tracker.model.JobApplication;
import com.jobtracker.job_tracker.service.JobApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin("*")
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<JobApplication>> getAllApplications() {
        return ResponseEntity.ok(service.getAllApplications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getApplicationById(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<JobApplication>> getByStatus(@PathVariable ApplicationStatus status) {
        return ResponseEntity.ok(service.getByStatus(status));
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Long>> getStats() {
        return ResponseEntity.ok(service.getDashboardStats());
    }

    @PostMapping
    public ResponseEntity<JobApplication> addApplication(@RequestBody JobApplication application) {
        return ResponseEntity.status(201).body(service.addApplication(application));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobApplication> updateApplication(
            @PathVariable Long id,
            @RequestBody JobApplication application) {
        return ResponseEntity.ok(service.updateApplication(id, application));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<JobApplication> updateStatus(
            @PathVariable Long id,
            @RequestParam ApplicationStatus status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        service.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobApplication>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(service.searchByCompany(keyword));
    }
}