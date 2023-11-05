package com.project.Testing.controller;

import com.project.Testing.model.Company;
import com.project.Testing.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CompanyController {

    //Importing CompanyService.
    @Autowired
    private CompanyService companyService;

    //Visit companies page.
    @GetMapping("/companies")
    public String getAllCompanies(Model model) {
        model.addAttribute("companies", companyService.getAllCompanies());
        return "companies";
    }

    //Visit company details page using ID.
    @GetMapping("/company/{id}")
    public String getCompanyById(@PathVariable int id, Model model) {
        model.addAttribute("company", companyService.getCompanyById(id));
        return "company";
    }

    //Visit companies page after entering a search query.
    @PostMapping("/companies/search")
    public String searchCompanies(@RequestParam String companyName, Model model) {
        model.addAttribute("companies", companyService.searchCompanies(companyName));
        return "companies";
    }

    //Add companies (Not recomended!).
    @GetMapping("/companies/add")
    public String addCompanyForm(Model model) {
        model.addAttribute("company", new Company());
        return "addCompany";
    }
    @PostMapping("/companies/add")
    public String addCompany(@ModelAttribute("company") Company company) {
        companyService.addCompany(company);
        return "redirect:/companies";
    }

    //Company.js so we can retrieve js from that file.
    @GetMapping("/company.js")
    public String getJS(){
         return "company.js";
    }
}
