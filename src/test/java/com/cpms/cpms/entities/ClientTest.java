package com.cpms.cpms.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ClientTest {

    @Test
    public void testClientSettersAndGetters() {
        // Create a new Client instance
        Client client = new Client();

        // Set test values
        int clientID = 1;
        String clientName = "Jane Smith";
        String contactInfo = "jane@example.com";

        // Create a new User instance
        User user = new User();
        user.setUserID(100);
        user.setUserName("jane");
        user.setPassword("pw123");
        user.setRole(User.Role.Client); // Use the enum value for role

        // Set values in the Client instance
        client.setClientID(clientID);
        client.setClientName(clientName);
        client.setContactInfo(contactInfo);
        client.setUser(user);

        // Perform assertions to validate the Client instance
        Assertions.assertEquals(clientID, client.getClientID());
        Assertions.assertEquals(clientName, client.getClientName());
        Assertions.assertEquals(contactInfo, client.getContactInfo());
        Assertions.assertEquals(user, client.getUser());
    }
}