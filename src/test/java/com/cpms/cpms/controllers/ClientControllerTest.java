package com.cpms.cpms.controllers;

import com.cpms.cpms.entities.Client;
import com.cpms.cpms.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ClientControllerTest {

    private ClientController clientController; // Controller under test
    private ClientService clientServiceMock; // Mocked service dependency

    @BeforeEach
    public void setUp() {
        clientServiceMock = mock(ClientService.class); // Mock the ClientService
        clientController = new ClientController();

        // Inject the mocked service into the ClientController instance using reflection
        try {
            java.lang.reflect.Field field = ClientController.class.getDeclaredField("clientService");
            field.setAccessible(true);
            field.set(clientController, clientServiceMock);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock service", e);
        }
    }

    @Test
    public void shouldAddClient() {
        Client client = new Client();
        client.setClientName("HoneyBee");

        clientController.addClient(client);

        verify(clientServiceMock, times(1)).addClient(client); // Verify service interaction
    }

    @Test
    public void shouldGetClientById() {
        Client mockClient = new Client();
        mockClient.setClientName("HoneyBee");

        when(clientServiceMock.getClientById(1)).thenReturn(mockClient); // Mock service behavior

        Client result = clientController.getClientById(1);

        assertNotNull(result);
        assertEquals("HoneyBee", result.getClientName());
        verify(clientServiceMock, times(1)).getClientById(1); // Verify service interaction
    }

    @Test
    public void shouldGetAllClients() {
        Client client1 = new Client();
        client1.setClientName("Client1");
        Client client2 = new Client();
        client2.setClientName("Client2");

        List<Client> mockList = Arrays.asList(client1, client2);
        when(clientServiceMock.getAllClients()).thenReturn(mockList); // Mock service behavior

        List<Client> result = clientController.getAllClients();

        assertEquals(2, result.size());
        assertEquals("Client1", result.get(0).getClientName());
        assertEquals("Client2", result.get(1).getClientName());
        verify(clientServiceMock, times(1)).getAllClients(); // Verify service interaction
    }

    @Test
    public void shouldUpdateClient() {
        Client client = new Client();
        client.setClientID(1);
        client.setClientName("UpdatedClient");

        clientController.updateClient(client);

        verify(clientServiceMock, times(1)).updateClient(client); // Verify service interaction
    }

    @Test
    public void shouldDeleteClientById() {
        clientController.deleteClient(5);

        verify(clientServiceMock, times(1)).deleteClient(5); // Verify service interaction
    }
}