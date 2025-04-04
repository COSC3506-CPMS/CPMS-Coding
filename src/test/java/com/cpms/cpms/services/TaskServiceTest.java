package com.cpms.cpms.services;

import com.cpms.cpms.dao.TaskDAO;
import com.cpms.cpms.entities.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    private TaskService taskService; // Service under test
    private TaskDAO taskDAOMock; // Mocked DAO dependency

    @BeforeEach
    public void setUp() {
        taskDAOMock = Mockito.mock(TaskDAO.class); // Mock the DAO
        taskService = new TaskService(taskDAOMock); // Inject mock DAO into the service
    }

    @Test
    public void shouldAddTask() {
        Task task = new Task();
        task.setTaskName("Test Task");
        
        taskService.addTask(task);
        verify(taskDAOMock, times(1)).addTask(task); // Verify DAO interaction
    }

    @Test
    public void shouldGetTaskById() {
        Task task = new Task();
        task.setTaskName("Sample Task");
        
        when(taskDAOMock.getTask(1)).thenReturn(task); // Mock DAO behavior
        
        Task result = taskService.getTask(1);
        assertNotNull(result);
        assertEquals("Sample Task", result.getTaskName());
        verify(taskDAOMock, times(1)).getTask(1); // Verify DAO interaction
    }

    @Test
    public void shouldUpdateTask() {
        Task task = new Task();
        task.setTaskName("Updated Task");
        
        taskService.updateTask(task);
        verify(taskDAOMock, times(1)).updateTask(task); // Verify DAO interaction
    }

    @Test
    public void shouldDeleteTaskById() {
        taskService.deleteTask(1);
        verify(taskDAOMock, times(1)).deleteTask(1); // Verify DAO interaction
    }

    @Test
    public void shouldGetAllTasks() {
        Task task1 = new Task();
        task1.setTaskName("Task 1");
        Task task2 = new Task();
        task2.setTaskName("Task 2");
        
        List<Task> mockList = Arrays.asList(task1, task2);
        when(taskDAOMock.getAllTasks()).thenReturn(mockList); // Mock DAO behavior
        
        List<Task> result = taskService.getAllTasks();
        
        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getTaskName());
        assertEquals("Task 2", result.get(1).getTaskName());
        verify(taskDAOMock, times(1)).getAllTasks(); // Verify DAO interaction
    }
}
