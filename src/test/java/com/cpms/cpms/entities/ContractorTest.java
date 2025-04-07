package com.cpms.cpms.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ContractorTest {

    @Test
    public void testContractorSettersAndGetters() {
        // Arrange: Create a new Contractor object and mock User object
        Contractor contractor = new Contractor();
        int contractorID = 10;
        String contractorName = "John Doe";
        String contactInfo = "johndoe@example.com";
        
        User user = new User(); // Create and set properties for User object
        user.setUserID(5);

        // Act: Use setters to assign values
        contractor.setContractorID(contractorID);
        contractor.setContractorName(contractorName);
        contractor.setContactInfo(contactInfo);
        contractor.setContractorUserID(user);

        // Assert: Validate getter methods return correct values
        Assertions.assertEquals(contractorID, contractor.getContractorID(), "Contractor ID mismatch");
        Assertions.assertEquals(contractorName, contractor.getContractorName(), "Contractor Name mismatch");
        Assertions.assertEquals(contactInfo, contractor.getContactInfo(), "Contact Info mismatch");
        Assertions.assertEquals(user.getUserID(), contractor.getContractorUserID(), "Contractor User ID mismatch");
    }
}