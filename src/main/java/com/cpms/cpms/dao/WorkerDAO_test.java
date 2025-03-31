package com.cpms.cpms.dao;

import com.cpms.cpms.config.HibernateUtil;
import com.cpms.cpms.entities.Worker;
import com.cpms.cpms.entities.User;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class WorkerDAOTest {

    private static WorkerDAO workerDAO;

    @BeforeClass
    public static void setUp() {
        workerDAO = new WorkerDAO();
    }

    @Test
    public void testAddAndGetWorker() {
        Worker worker = new Worker();
        worker.setWorkerID(1);
        worker.setName("Alex Smith");
        worker.setContactInfo("alex.smith@example.com");

        User user = new User();
        user.setUserID(200);
        user.setUserName("alex");
        user.setPassword("password123");
        user.setRole("Worker");
        worker.setUser(user);

        workerDAO.addWorker(worker);

        Worker result = workerDAO.getWorker(1);
        assertNotNull(result);
        assertEquals("Alex Smith", result.getName());
    }

    @Test
    public void testUpdateWorker() {
        Worker worker = workerDAO.getWorker(1);
        worker.setContactInfo("newcontact@example.com");
        workerDAO.updateWorker(worker);

        Worker updated = workerDAO.getWorker(1);
        assertEquals("newcontact@example.com", updated.getContactInfo());
    }

    @Test
    public void testGetAllWorkers() {
        List<Worker> workers = workerDAO.getAllWorkers();
        assertTrue(workers.size() >= 1);
    }

    @Test
    public void testDeleteWorker() {
        workerDAO.deleteWorker(1);
        Worker deleted = workerDAO.getWorker(1);
        assertNull(deleted);
    }

    @AfterClass
    public static void tearDown() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        if (factory != null) factory.close();
    }
}
