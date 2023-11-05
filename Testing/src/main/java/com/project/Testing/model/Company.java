package com.project.Testing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Company {

    //Id auto-generating @Id makes it unique and primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;
    private String ticker;
    private String companyName;

    public Company() {
        // You can initialize any default values here if needed
    }

    //Super class
    public Company(int id) {
        super();
    }

    //Constructor
    public Company(int companyId, String ticker, String companyName) {
        this.companyId = companyId;
        this.ticker = ticker;
        this.companyName = companyName;
    }

    //Getter ans Setters
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
