package com.project.Testing.repository;

import com.project.Testing.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {

    //This is Company Repository
    List<Company> findByTickerContaining(String ticker);
    List<Company> findByCompanyNameContaining(String companyName);
}