package com.cpms.cpms.entities;

import org.junit.jupiter.api.Test; // Using JUnit 5 for testing
import org.junit.jupiter.api.Assertions; // Updated import for assertions

public class ContractorTest {

    @Test
    public void testContractorSettersAndGetters() {
        // Create a new Contractor object
        Contractor contractor = new Contractor();

        // Values to test
        int contractorID = 10;
        String contractorName = "John Doe";
        String contactInfo = "johndoe@example.com";

        // Mock User object
        User user = new User();
        user.setUserID(5); // Assigning a sample UserID

        // Set values using setter methods
        contractor.setContractorID(contractorID);
        contractor.setContractorName(contractorName);
        contractor.setContactInfo(contactInfo);
        contractor.setUser(user);

        // Validate values using getter methods and assertions
        Assertions.assertEquals(contractorID, contractor.getContractorID());
        Assertions.assertEquals(contractorName, contractor.getContractorName());
        Assertions.assertEquals(contactInfo, contractor.getContactInfo());
        Assertions.assertEquals(user, contractor.getUser()); // Assert using associated User object
    }
}