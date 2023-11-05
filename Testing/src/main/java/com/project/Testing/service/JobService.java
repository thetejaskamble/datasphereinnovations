package com.project.Testing.service;

import com.project.Testing.model.Job;

import java.util.List;

// Service
public interface JobService {
    List<Job> getAllJobs();
    Job getJobById(Long id);
    List<Job> getJobsByRole(String role);
    List<Job> getJobsByLocation(String location);
    List<Job> searchJobs(String search);
    List<Job> searchingJobs(String search);
    List<Job> filterJobs(String role, String location, String city, String industry);
    List<Job> sortJobs(String sort);
    void saveJob(Job job);
    void addJob(Job job);
   // List<Job> searchJobs(String role, String location, String industry, Integer salary, String companyName);


}