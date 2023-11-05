package com.project.Testing.service;

import com.project.Testing.model.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getAllCompanies();
    Company getCompanyById(int id);
    List<Company> searchCompanies(String companyName);
    void addCompany(Company company);
}