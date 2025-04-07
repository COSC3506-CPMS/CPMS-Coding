package com.cpms.cpms.controllers;

import com.cpms.cpms.services.ContractorService;
import com.cpms.cpms.entities.Contractor;
import java.util.List;

public class ContractorController {
    private ContractorService contractorService;

    // Constructor-based dependency injection
    public ContractorController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    // Adds a new contractor
    public void addContractor(String name, String contactInfo) {
        if (name == null || name.isEmpty() || contactInfo == null || contactInfo.isEmpty()) {
            throw new IllegalArgumentException("Name and contact info must not be null or empty");
        }
        
        Contractor contractor = new Contractor();
        contractor.setContractorName(name);
        contractor.setContactInfo(contactInfo); // Corrected parameter used here

        contractorService.addContractor(contractor); // Save contractor
    }

    // Gets a contractor by ID
    public Contractor getContractorById(int id) {
        return contractorService.getContractor(id); // Retrieve contractor by ID
    }

    // Updates a contractor's details
    public void updateContractor(Contractor contractor) {
        contractorService.updateContractor(contractor); // Update contractor
    }

    // Deletes a contractor by ID
    public void deleteContractor(int id) {
        contractorService.deleteContractor(id); // Delete contractor by ID
    }

    // Retrieves a list of all contractors
    public List<Contractor> getAllContractors() {
        return contractorService.getAllContractors(); // Retrieve all contractors
    }
}