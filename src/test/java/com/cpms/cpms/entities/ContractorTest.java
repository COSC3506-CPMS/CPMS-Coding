package com.cpms.cpms.entities;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContractorTest {

    @Test
    public void testSettersAndGetters() {
        // Create a User instance (assuming the User class exists)
        User user = new User();
        
        // Create a Contractor instance
        Contractor contractor = new Contractor();

        // Test setter and getter for contractorID
        contractor.setContractorID(1);
        Assertions.assertEquals(1, contractor.getContractorID());

        // Test setter and getter for user
        contractor.setUser(user);
        Assertions.assertEquals(user, contractor.getUser());

        // Test setter and getter for contractorName
        contractor.setContractorName("John Doe");
        Assertions.assertEquals("John Doe", contractor.getContractorName());

        // Test setter and getter for contactInfo
        contractor.setContactInfo("john.doe@example.com");
        Assertions.assertEquals("john.doe@example.com", contractor.getContactInfo());
    }
}