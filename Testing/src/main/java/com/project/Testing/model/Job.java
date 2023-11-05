package com.project.Testing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Job {

    //Id auto-generating @Id makes it unique and primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;
    private String industry;
    private String companyName;
    private String ticker;
    private String location;
    private String city;
    private int salary;
    private String description;

    // getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Job(Long id, String role, String industry, String companyName, String ticker, String location, String city, int salary, String description) {
        this.id = id;
        this.role = role;
        this.industry = industry;
        this.companyName = companyName;
        this.ticker = ticker;
        this.location = location;
        this.city = city;
        this.salary = salary;
        this.description = description;
    }

    //Tostring

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", industry='" + industry + '\'' +
                ", companyName='" + companyName + '\'' +
                ", ticker='" + ticker + '\'' +
                ", location='" + location + '\'' +
                ", city='" + city + '\'' +
                ", salary=" + salary +
                ", description='" + description + '\'' +
                '}';
    }

    public Job() {
    }
}