package com.cpms.cpms.controllers;

import com.cpms.cpms.services.ContractorService;
import com.cpms.cpms.entities.Contractor;
import java.util.List;

public class ContractorController {
    private ContractorService contractorService = new ContractorService(); // Inject ContractorService

    public void addContractor(String name, String specialty, String contactInfo) {
        Contractor contractor = new Contractor();
        contractor.setContractorName(name);
        contractor.setContactInfo(contactInfo);
        contractorService.addContractor(contractor); // Save contractor
    }

    public Contractor getContractorById(int id) {
        return contractorService.getContractor(id); // Retrieve contractor by ID
    }

    public void updateContractor(Contractor contractor) {
        contractorService.updateContractor(contractor); // Update contractor
    }

    public void deleteContractor(int id) {
        contractorService.deleteContractor(id); // Delete contractor by ID
    }

    public List<Contractor> getAllContractors() {
        return contractorService.getAllContractors(); // Retrieve all contractors
    }
}

