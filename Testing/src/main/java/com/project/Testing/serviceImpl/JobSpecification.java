package com.project.Testing.serviceImpl;

import com.project.Testing.model.Job;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class JobSpecification {
    public static Specification<Job> jobHas(String search) {
        return (root, query, cb) -> cb.like(root.get("role"), "%" + query + "%");
    }

    public static Specification<Job> jobInRole(String role) {
        return (root, query, cb) -> cb.equal(root.get("role"), role);
    }

    public static Specification<Job> jobInLocation(String location) {
        return (root, query, cb) -> cb.equal(root.get("location"), location);
    }

    public static Specification<Job> jobInCity(String city) {
        return (root, query, cb) -> cb.equal(root.get("city"), city);
    }

    public static Specification<Job> jobInIndustry(String industry) {
        return (root, query, cb) -> cb.equal(root.get("industry"), industry);
    }

    public static Specification<Job> filterJobs(String role, String location, String industry, Integer salary, String companyName) {
        return (root, query, builder) -> {
            Predicate predicate = builder.conjunction();

            if (role != null && !role.isEmpty()) {
                predicate = builder.and(predicate, builder.like(root.get("role"), "%" + role + "%"));
            }

            if (location != null && !location.isEmpty()) {
                predicate = builder.and(predicate, builder.like(root.get("location"), "%" + location + "%"));
            }

            if (industry != null && !industry.isEmpty()) {
                predicate = builder.and(predicate, builder.like(root.get("industry"), "%" + industry + "%"));
            }

            if (salary != null) {
                predicate = builder.and(predicate, builder.equal(root.get("salary"), salary));
            }

            if (companyName != null && !companyName.isEmpty()) {
                predicate = builder.and(predicate, builder.like(root.get("companyName"), "%" + companyName + "%"));
            }

            return predicate;
        };
    }
}