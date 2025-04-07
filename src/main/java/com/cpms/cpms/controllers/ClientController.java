package com.cpms.cpms.controllers;

import com.cpms.cpms.entities.Client;
import com.cpms.cpms.services.ClientService;
import java.util.List;

public class ClientController {
    private ClientService clientService;

    public ClientController() {
        this.clientService = new ClientService();
    }

    // Adds a new client
    public void addClient(Client client) {
        clientService.addClient(client);
    }

    // Retrieves a client by their ID
    public Client getClientById(int id) {
        return clientService.getClientById(id);
    }

    // Retrieves all clients
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // Updates an existing client
    public void updateClient(Client client) {
        clientService.updateClient(client);
    }

    // Deletes a client by their ID
    public void deleteClient(int id) {
        clientService.deleteClient(id);
    }
}