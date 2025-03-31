package com.cpms.cpms.services;

import com.cpms.cpms.dao.ContractorDAO;
import com.cpms.cpms.entities.Contractor;
import java.util.List;

public class ContractorService {
    private ContractorDAO contractorDAO = new ContractorDAO();

    // Adds a new contractor by delegating the task to ContractorDAO
    public void addContractor(Contractor contractor) {
        contractorDAO.addContractor(contractor);
    }

    // Retrieves a contractor by their ID using ContractorDAO
    public Contractor getContractor(int contractorID) {
        return contractorDAO.getContractor(contractorID);
    }

    // Updates an existing contractor by delegating the task to ContractorDAO
    public void updateContractor(Contractor contractor) {
        contractorDAO.updateContractor(contractor);
    }

    // Deletes a contractor by their ID using ContractorDAO
    public void deleteContractor(int contractorID) {
        contractorDAO.deleteContractor(contractorID);
    }

    // Retrieves all contractors using ContractorDAO
    public List<Contractor> getAllContractors() {
        return contractorDAO.getAllContractors();
    }
}
