package com.cpms.cpms.dao;

import com.cpms.cpms.config.HibernateUtil;
import com.cpms.cpms.entities.Client;
import com.cpms.cpms.entities.User;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class ClientDAOTest {

    private static ClientDAO clientDAO;

    @BeforeClass
    public static void setUp() {
        clientDAO = new ClientDAO();
    }

    @Test
    public void testAddAndGetClient() {
        Client client = new Client();
        client.setClientID(1);
        client.setClientName("John Client");
        client.setContactInfo("john@example.com");

        User user = new User();
        user.setUserID(100);
        user.setUserName("john");
        user.setPassword("pass");
        user.setRole("Client");
        client.setUser(user);

        clientDAO.addClient(client);

        Client retrieved = clientDAO.getClient(1);
        assertNotNull(retrieved);
        assertEquals("John Client", retrieved.getClientName());
    }

    @Test
    public void testUpdateClient() {
        Client client = clientDAO.getClient(1);
        client.setContactInfo("new_contact@example.com");
        clientDAO.updateClient(client);

        Client updated = clientDAO.getClient(1);
        assertEquals("new_contact@example.com", updated.getContactInfo());
    }

    @Test
    public void testGetAllClients() {
        List<Client> clients = clientDAO.getAllClients();
        assertTrue(clients.size() >= 1);
    }

    @Test
    public void testDeleteClient() {
        clientDAO.deleteClient(1);
        Client deleted = clientDAO.getClient(1);
        assertNull(deleted);
    }

    @AfterClass
    public static void tearDown() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        if (factory != null) factory.close();
    }
}
