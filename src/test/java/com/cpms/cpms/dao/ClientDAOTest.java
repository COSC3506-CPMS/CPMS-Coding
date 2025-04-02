package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Client;
import com.cpms.cpms.entities.User;

public class ClientDAOTest {
    public static void main(String[] args) {
        ClientDAO clientDAO = new ClientDAO();

        // Test adding a client
        System.out.println("---- Testing Add Client ----");
        Client client = new Client();
        client.setClientName("HoneyBee"); // Client Name
        client.setContactInfo("honeybee48@yahoo.com"); // Contact Info

        User user = new User();
        user.setUserID(10); // User ID
        user.setUserName("Honey35U"); // Username
        user.setPassword("bee$honey"); // Password
        user.setRole(User.Role.Client); // Enum for role
        client.setUser(user);

        clientDAO.addClient(client); // Add client
        System.out.println("Client added: " + client);

        // Test getting a client by ID
        System.out.println("---- Testing Get Client by ID ----");
        Client retrievedClient = clientDAO.getClient(client.getClientID()); // Get Client ID dynamically
        if (retrievedClient != null) {
            System.out.println("Retrieved Client: " + retrievedClient);
        }

        // Test updating a client
        System.out.println("---- Testing Update Client ----");
        if (retrievedClient != null) {
            retrievedClient.setContactInfo("updated_honeybee@yahoo.com");
            clientDAO.updateClient(retrievedClient);
            System.out.println("Client updated: " + retrievedClient);
        }

        // Test retrieving all clients
        System.out.println("---- Testing Get All Clients ----");
        System.out.println("All Clients: " + clientDAO.getAllClients());

        // Test deleting a client
        System.out.println("---- Testing Delete Client ----");
        if (retrievedClient != null) {
            clientDAO.deleteClient(retrievedClient.getClientID());
            System.out.println("Client deleted successfully!");
        }
    }
}