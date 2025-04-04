package com.cpms.cpms.entities;

import org.junit.Assert;
import org.junit.Test;

public class ClientTest {

    @Test
    public void testClientSettersAndGetters() {
        Client client = new Client();

        int clientID = 123;
        String clientName = "Jane Doe";
        String contactInfo = "jane.doe@example.com";

        User user = new User();
        user.setUserID(99);
        user.setUserName("jane");
        user.setPassword("securepw");
        user.setRole("Client");

        client.setClientID(clientID);
        client.setClientName(clientName);
        client.setContactInfo(contactInfo);
        client.setUser(user);
      
        Assert.assertEquals(clientID, client.getClientID());
        Assert.assertEquals(clientName, client.getClientName());
        Assert.assertEquals(contactInfo, client.getContactInfo());
        Assert.assertEquals(user, client.getUser());
    }
}
