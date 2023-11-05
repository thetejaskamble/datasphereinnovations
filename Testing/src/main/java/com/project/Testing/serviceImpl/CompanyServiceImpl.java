package com.project.Testing.serviceImpl;

import com.project.Testing.model.Company;
import com.project.Testing.repository.CompanyRepo;
import com.project.Testing.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyServiceImpl implements CompanyService {

    //Importing Company Repository
    @Autowired
    private CompanyRepo companyRepository;

    //Get all companies
    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }


    //Get a specific by company id
    @Override
    public Company getCompanyById(int id) {
        return companyRepository.findById(id).orElse(null);
    }

    //Get companies searched by user s
    @Override
    public List<Company> searchCompanies(String companyName) {
        return companyRepository.findByCompanyNameContaining(companyName);
    }

    //Add company
    @Override
    public void addCompany(Company company) {
        companyRepository.save(company);
    }
}