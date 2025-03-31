package com.cpms.cpms.entities;

import org.junit.Assert;
import org.junit.Test;

public class ContractorTest {

    @Test
    public void testContractorSettersAndGetters() {
        Contractor contractor = new Contractor();
      
        int testID = 1;
        String testName = "Test Contractor";
        String testContact = "test@example.com";

        User testUser = new User();
        testUser.setUserID(100); 

        contractor.setContractorID(testID);
        contractor.setContractorName(testName);
        contractor.setContactInfo(testContact);
        contractor.setUser(testUser);

        Assert.assertEquals(testID, contractor.getContractorID());
        Assert.assertEquals(testName, contractor.getContractorName());
        Assert.assertEquals(testContact, contractor.getContactInfo());
        Assert.assertEquals(testUser, contractor.getUser());
    }
}
