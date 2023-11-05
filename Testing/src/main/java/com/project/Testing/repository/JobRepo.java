package com.project.Testing.repository;

import com.project.Testing.model.Job;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepo extends JpaRepository<Job, Long> {

    ////This is Job Repository
    List<Job> findByRole(String role);
    List<Job> findByLocation(String location);
    List<Job> findAll(Specification<Job> and);
    List<Job> findByRoleContaining(String search);

}
