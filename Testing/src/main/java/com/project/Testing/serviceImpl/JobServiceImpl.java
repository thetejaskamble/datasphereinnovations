package com.project.Testing.serviceImpl;

import com.project.Testing.model.Job;
import com.project.Testing.repository.JobRepo;
import com.project.Testing.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepo jobRepository;
    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }
    @Override
    public List<Job> getJobsByRole(String role) {
        return jobRepository.findByRole(role);
    }
    @Override
    public List<Job> getJobsByLocation(String location) {
        return jobRepository.findByLocation(location);
    }
    @Override
    public List<Job> searchJobs(String search) {
        return jobRepository.findAll(Specification.where(JobSpecification.jobHas(search)));
    }

    @Override
    public List<Job> searchingJobs(String search) {
        return jobRepository.findByRoleContaining(search);
    }

    @Override
    public List<Job> filterJobs(String role, String location, String city, String industry) {
        return jobRepository.findAll(Specification.where(JobSpecification.jobInRole(role)).and(JobSpecification.jobInLocation(location)).and(JobSpecification.jobInCity(city)).and(JobSpecification.jobInIndustry(industry)));
    }
    @Override
    public void addJob(Job job) {
        jobRepository.save(job);
    }
    @Override
    public List<Job> sortJobs(String sort) {
        List<Sort.Order> criteria;
        return jobRepository.findAll(Sort.by(sort));
    }
    @Override
    public void saveJob(Job job) {
        jobRepository.save(job);
    }


}