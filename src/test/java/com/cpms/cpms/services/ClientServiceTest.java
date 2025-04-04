package com.cpms.cpms.services;

import com.cpms.cpms.dao.ClientDAO;
import com.cpms.cpms.entities.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClientServiceTest {

    private ClientService clientService; // Service under test
    private ClientDAO clientDAOMock; // Mocked DAO dependency

    @BeforeEach
    public void setUp() {
        clientDAOMock = mock(ClientDAO.class); // Mock the DAO
        clientService = new ClientService(); // Create service instance

        // Inject the mocked DAO into the ClientService instance using reflection
        try {
            java.lang.reflect.Field field = ClientService.class.getDeclaredField("clientDAO");
            field.setAccessible(true);
            field.set(clientService, clientDAOMock);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock DAO", e);
        }
    }

    @Test
    public void shouldAddClient() {
        Client client = new Client();
        client.setClientName("HoneyBee");

        clientService.addClient(client);

        verify(clientDAOMock, times(1)).addClient(client); // Verify DAO interaction
    }

    @Test
    public void shouldGetClientById() {
        Client mockClient = new Client();
        mockClient.setClientName("HoneyBee");

        when(clientDAOMock.getClient(1)).thenReturn(mockClient); // Mock DAO behavior

        Client result = clientService.getClientById(1);

        assertNotNull(result);
        assertEquals("HoneyBee", result.getClientName());
        verify(clientDAOMock, times(1)).getClient(1); // Verify DAO interaction
    }

    @Test
    public void shouldGetAllClients() {
        Client client1 = new Client();
        client1.setClientName("Client1");
        Client client2 = new Client();
        client2.setClientName("Client2");

        List<Client> mockList = Arrays.asList(client1, client2);
        when(clientDAOMock.getAllClients()).thenReturn(mockList); // Mock DAO behavior

        List<Client> result = clientService.getAllClients();

        assertEquals(2, result.size());
        assertEquals("Client1", result.get(0).getClientName());
        assertEquals("Client2", result.get(1).getClientName());
        verify(clientDAOMock, times(1)).getAllClients(); // Verify DAO interaction
    }

    @Test
    public void shouldUpdateClient() {
        Client client = new Client();
        client.setClientID(1);
        client.setClientName("UpdatedClient");

        clientService.updateClient(client);

        verify(clientDAOMock, times(1)).updateClient(client); // Verify DAO interaction
    }

    @Test
    public void shouldDeleteClientById() {
        clientService.deleteClient(5);

        verify(clientDAOMock, times(1)).deleteClient(5); // Verify DAO interaction
    }
}