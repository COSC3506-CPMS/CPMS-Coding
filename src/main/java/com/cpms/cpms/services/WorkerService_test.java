package com.cpms.cpms.services;

import com.cpms.cpms.dao.WorkerDAO;
import com.cpms.cpms.entities.Worker;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WorkerServiceTest {

    private WorkerService workerService;
    private WorkerDAO workerDAOMock;

    @Before
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

    @Test
    public void testAddWorker() {
        Worker worker = new Worker();
        workerService.addWorker(worker);
        verify(workerDAOMock).addWorker(worker);
    }

    @Test
    public void testGetWorkerById() {
        Worker worker = new Worker();
        when(workerDAOMock.getWorker(1)).thenReturn(worker);

        Worker result = workerService.getWorkerById(1);
        assertEquals(worker, result);
    }

    @Test
    public void testGetAllWorkers() {
        List<Worker> mockList = Arrays.asList(new Worker(), new Worker());
        when(workerDAOMock.getAllWorkers()).thenReturn(mockList);

        List<Worker> result = workerService.getAllWorkers();
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateWorker() {
        Worker worker = new Worker();
        workerService.updateWorker(worker);
        verify(workerDAOMock).updateWorker(worker);
    }

    @Test
    public void testDeleteWorker() {
        workerService.deleteWorker(99);
        verify(workerDAOMock).deleteWorker(99);
    }
}
