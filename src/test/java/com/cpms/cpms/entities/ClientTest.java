package com.cpms.cpms.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class ClientTest {

    @Test
    public void testClientSettersAndGetters() {
        // Create a new Client instance
        Client client = new Client();

        // Set test values
        int clientID = 4; // Client ID
        String clientName = "HoneyBee"; // Client Name
        String contactInfo = "honeybee48@yahoo.com"; // Contact Info

        // Create a new User instance
        User user = new User();
        user.setUserID(10); // User ID
        user.setUserName("Honey35U");
        user.setPassword("bee$honey"); // Example password
        user.setRole(User.Role.Client); // Use the enum value for role

        // Set values in the Client instance
        client.setClientID(clientID);
        client.setClientName(clientName);
        client.setContactInfo(contactInfo);
        client.setUser(user);

        // Perform assertions to validate the Client instance
        Assertions.assertEquals(clientID, client.getClientID(), "Client ID should match");
        Assertions.assertEquals(clientName, client.getClientName(), "Client name should match");
        Assertions.assertEquals(contactInfo, client.getContactInfo(), "Contact info should match");
        Assertions.assertEquals(user, client.getUser(), "User object should match");

        // Perform assertions to validate the associated User instance
        Assertions.assertEquals(10, client.getUser().getUserID(), "User ID should match");
        Assertions.assertEquals("Honey35U", client.getUser().getUserName(), "User name should match");
        Assertions.assertEquals("bee$honey", client.getUser().getPassword(), "User password should match");
        Assertions.assertEquals(User.Role.Client, client.getUser().getRole(), "User role should match");
    }
}