package com.cpms.cpms.services;

import com.cpms.cpms.dao.ClientDAO;
import com.cpms.cpms.entities.Client;
import java.util.List;

public class ClientService {
    private ClientDAO clientDAO;

    public ClientService() {
        this.clientDAO = new ClientDAO();
    }

    // Adds a new client to the database
    public void addClient(Client client) {
        clientDAO.addClient(client);
    }

    // Retrieves a client by their ID
    public Client getClientById(int id) {
        return clientDAO.getClient(id);
    }

    // Retrieves all clients from the database
    public List<Client> getAllClients() {
        return clientDAO.getAllClients();
    }

    // Updates an existing client's details
    public void updateClient(Client client) {
        clientDAO.updateClient(client);
    }

    // Deletes a client by their ID
    public void deleteClient(int id) {
        clientDAO.deleteClient(id);
    }
}