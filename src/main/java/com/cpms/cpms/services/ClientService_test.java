package com.cpms.cpms.services;

import com.cpms.cpms.dao.ClientDAO;
import com.cpms.cpms.entities.Client;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ClientServiceTest {

    private ClientService clientService;
    private ClientDAO clientDAOMock;

    @Before
    public void setUp() throws Exception {
        clientDAOMock = mock(ClientDAO.class);
        clientService = new ClientService();
      
        java.lang.reflect.Field field = ClientService.class.getDeclaredField("clientDAO");
        field.setAccessible(true);
        field.set(clientService, clientDAOMock);
    }

    @Test
    public void testAddClient() {
        Client client = new Client();
        clientService.addClient(client);
        verify(clientDAOMock).addClient(client);
    }

    @Test
    public void testGetClientById() {
        Client mockClient = new Client();
        when(clientDAOMock.getClient(1)).thenReturn(mockClient);

        Client result = clientService.getClientById(1);
        assertEquals(mockClient, result);
    }

    @Test
    public void testGetAllClients() {
        List<Client> mockList = Arrays.asList(new Client(), new Client());
        when(clientDAOMock.getAllClients()).thenReturn(mockList);

        List<Client> result = clientService.getAllClients();
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateClient() {
        Client client = new Client();
        clientService.updateClient(client);
        verify(clientDAOMock).updateClient(client);
    }

    @Test
    public void testDeleteClient() {
        clientService.deleteClient(5);
        verify(clientDAOMock).deleteClient(5);
    }
}
