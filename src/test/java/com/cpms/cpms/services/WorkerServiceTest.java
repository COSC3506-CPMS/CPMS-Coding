package com.cpms.cpms.services;

import com.cpms.cpms.dao.WorkerDAO;
import com.cpms.cpms.entities.Worker;
import com.cpms.cpms.entities.Worker.Availability;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class WorkerServiceTest {

    private WorkerService workerService;
    private WorkerDAO workerDAOMock;

    // Set up mocked DAO and service
    @BeforeEach
    public void setUp() {
        workerDAOMock = Mockito.mock(WorkerDAO.class);
        workerService = new WorkerService();

        try {
            java.lang.reflect.Field daoField = WorkerService.class.getDeclaredField("workerDAO");
            daoField.setAccessible(true);
            daoField.set(workerService, workerDAOMock);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock DAO", e);
        }
    }

    // Test adding a worker
    @Test
    public void shouldAddWorkerWhenWorkerDetailsAreValid() {
        Worker worker = new Worker();
        worker.setWorkerName("John Doe");
        worker.setContactInfo("john.doe@example.com");
        worker.setAvailability(Availability.AVAILABLE);
        worker.setSpecialty("Electrician");

        workerService.addWorker(worker);

        verify(workerDAOMock, times(1)).addWorker(worker);
    }

    // Test retrieving a worker by ID
    @Test
    public void shouldGetWorkerById() {
        Worker worker = new Worker();
        worker.setWorkerName("Jane Smith");
        worker.setContactInfo("jane.smith@example.com");

        when(workerDAOMock.getWorker(1)).thenReturn(worker);

        Worker result = workerService.getWorkerById(1);

        assertNotNull(result);
        assertEquals("Jane Smith", result.getWorkerName());
        assertEquals("jane.smith@example.com", result.getContactInfo());
        verify(workerDAOMock, times(1)).getWorker(1);
    }

    // Test updating a worker
    @Test
    public void shouldUpdateWorker() {
        Worker worker = new Worker();
        worker.setWorkerID(1);
        worker.setWorkerName("Updated Worker");
        worker.setContactInfo("updated.worker@example.com");

        workerService.updateWorker(worker);

        verify(workerDAOMock, times(1)).updateWorker(worker);
    }

    // Test deleting a worker by ID
    @Test
    public void shouldDeleteWorkerById() {
        workerService.deleteWorker(5);

        verify(workerDAOMock, times(1)).deleteWorker(5);
    }

    // Test retrieving all workers
    @Test
    public void shouldGetAllWorkers() {
        Worker worker1 = new Worker();
        worker1.setWorkerName("Worker Alpha");
        Worker worker2 = new Worker();
        worker2.setWorkerName("Worker Beta");

        List<Worker> mockList = Arrays.asList(worker1, worker2);
        when(workerDAOMock.getAllWorkers()).thenReturn(mockList);

        List<Worker> result = workerService.getAllWorkers();

        assertEquals(2, result.size());
        assertEquals("Worker Alpha", result.get(0).getWorkerName());
        assertEquals("Worker Beta", result.get(1).getWorkerName());
        verify(workerDAOMock, times(1)).getAllWorkers();
    }
}