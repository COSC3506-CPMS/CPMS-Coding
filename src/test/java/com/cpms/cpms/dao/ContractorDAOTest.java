package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Contractor;
import com.cpms.cpms.entities.User;

public class ContractorDAOTest {
    public static void main(String[] args) {
        ContractorDAO contractorDAO = new ContractorDAO();

        // Test adding a contractor
        System.out.println("---- Testing Add Contractor ----");
        Contractor contractor = new Contractor();

        // Assign user with role "Contractor" (using UserID = 5 as an example)
        User user = new User();
        user.setUserID(5); // Reference UserID 5 with role Contractor
        user.setUserName("harman142");
        user.setRole(User.Role.Contractor);
        user.setPermissions("READ, WRITE");

        // Set contractor details
        contractor.setContractorUserID(user);
        contractor.setContractorName("Harman Singh");
        contractor.setContactInfo("harman142@example.com");

        contractorDAO.addContractor(contractor); // Add contractor
        System.out.println("Contractor added: " + contractor);

        // Test getting a contractor by ID
        System.out.println("---- Testing Get Contractor by ID ----");
        Contractor retrievedContractor = contractorDAO.getContractor(contractor.getContractorID());
        if (retrievedContractor != null) {
            System.out.println("Retrieved Contractor: " + retrievedContractor);
        }

        // Test updating a contractor
        System.out.println("---- Testing Update Contractor ----");
        if (retrievedContractor != null) {
            retrievedContractor.setContactInfo("updated_harman142@example.com");
            contractorDAO.updateContractor(retrievedContractor);
            System.out.println("Contractor updated: " + retrievedContractor);
        }

        // Test retrieving all contractors
        System.out.println("---- Testing Get All Contractors ----");
        System.out.println("All Contractors: " + contractorDAO.getAllContractors());

        // Test deleting a contractor
        System.out.println("---- Testing Delete Contractor ----");
        if (retrievedContractor != null) {
            contractorDAO.deleteContractor(retrievedContractor.getContractorID());
            System.out.println("Contractor deleted successfully!");
        }
    }
}