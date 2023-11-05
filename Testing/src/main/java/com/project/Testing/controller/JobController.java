package com.project.Testing.controller;

import com.project.Testing.model.Job;
import com.project.Testing.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JobController {

    //Importing JobService.
    @Autowired
    private JobService jobService;

    //Visit jobs page.
    @GetMapping("/jobs")
    public String viewJobs(@RequestParam(required = false) String search,
                           @RequestParam(required = false) String role,
                           @RequestParam(required = false) String location,
                           @RequestParam(required = false) String sort,
                           @RequestParam(required = false) String city,
                           @RequestParam(required = false) String industry,
                           Model model) {
        List<Job> jobs;
        if (search != null) {
            jobs = jobService.searchingJobs(search);
        } else if (role != null || location != null || city!=null) {
            jobs = jobService.filterJobs(role, location, city, industry);
        } else if (sort != null) {
            jobs = jobService.sortJobs(sort);
        } else {
            jobs = jobService.getAllJobs();
        }
        model.addAttribute("jobs", jobs);
        return "jobs";
    }

    //Visit job details page.
    @GetMapping("/job/{id}")
    public String viewJobDetails(@PathVariable Long id, Model model) {
        model.addAttribute("job", jobService.getJobById(id));
        return "jobDetails";
    }

    //Add jobs.
    @GetMapping("/jobs/add")
    public String showAddJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "addJob";
    }
    @PostMapping("/jobs/add")
    public String addJob(@ModelAttribute("job") Job job) {
        jobService.addJob(job);
        return "redirect:/jobs";
    }
}