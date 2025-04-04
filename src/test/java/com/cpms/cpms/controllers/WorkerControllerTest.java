package com.cpms.cpms.controllers;

import com.cpms.cpms.entities.Worker;
import com.cpms.cpms.entities.Worker.Availability;
import com.cpms.cpms.services.WorkerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for WorkerController.
 */
public class WorkerControllerTest {

    private WorkerController workerController; // Controller under test
    private WorkerService workerServiceMock; // Mocked service dependency

    // Sets up the mocked service and injects it into the controller
    @BeforeEach
    public void setUp() {
        workerServiceMock = Mockito.mock(WorkerService.class); // Mock the service
        workerController = new WorkerController(); // Initialize WorkerController

        try {
            java.lang.reflect.Field serviceField = WorkerController.class.getDeclaredField("workerService");
            serviceField.setAccessible(true);
            serviceField.set(workerController, workerServiceMock);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock service", e);
        }
    }

    // Tests adding a worker
    @Test
    public void shouldAddWorker() {
        Worker worker = new Worker();
        worker.setWorkerName("John Doe");
        worker.setContactInfo("john.doe@example.com");
        worker.setAvailability(Availability.AVAILABLE);
        worker.setSpecialty("Electrician");

        workerController.addWorker(worker);

        verify(workerServiceMock, times(1)).addWorker(worker); // Verify service interaction
    }

    // Tests retrieving a worker by ID
    @Test
    public void shouldGetWorkerById() {
        Worker worker = new Worker();
        worker.setWorkerName("Jane Smith");
        worker.setContactInfo("jane.smith@example.com");

        when(workerServiceMock.getWorkerById(1)).thenReturn(worker); // Mock service behavior

        Worker result = workerController.getWorkerById(1);

        assertNotNull(result);
        assertEquals("Jane Smith", result.getWorkerName());
        assertEquals("jane.smith@example.com", result.getContactInfo());
        verify(workerServiceMock, times(1)).getWorkerById(1); // Verify service interaction
    }

    // Tests retrieving all workers
    @Test
    public void shouldGetAllWorkers() {
        Worker worker1 = new Worker();
        worker1.setWorkerName("Worker Alpha");
        Worker worker2 = new Worker();
        worker2.setWorkerName("Worker Beta");

        List<Worker> mockList = Arrays.asList(worker1, worker2);
        when(workerServiceMock.getAllWorkers()).thenReturn(mockList); // Mock service behavior

        List<Worker> result = workerController.getAllWorkers();

        assertEquals(2, result.size());
        assertEquals("Worker Alpha", result.get(0).getWorkerName());
        assertEquals("Worker Beta", result.get(1).getWorkerName());
        verify(workerServiceMock, times(1)).getAllWorkers(); // Verify service interaction
    }

    // Tests updating a worker
    @Test
    public void shouldUpdateWorker() {
        Worker worker = new Worker();
        worker.setWorkerID(1);
        worker.setWorkerName("Updated Worker");
        worker.setContactInfo("updated.worker@example.com");

        workerController.updateWorker(worker);

        verify(workerServiceMock, times(1)).updateWorker(worker); // Verify service interaction
    }

    // Tests deleting a worker by ID
    @Test
    public void shouldDeleteWorkerById() {
        workerController.deleteWorker(5);

        verify(workerServiceMock, times(1)).deleteWorker(5); // Verify service interaction
    }
}