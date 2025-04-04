// TaskControllerTest.java
package com.cpms.cpms.controllers;

import com.cpms.cpms.entities.Task;
import com.cpms.cpms.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Test class for TaskController
public class TaskControllerTest {

    private TaskController taskController;
    private TaskService taskService;

    // Set up the test environment before each test case
    @BeforeEach
    public void setUp() {
        taskService = mock(TaskService.class); // Mock TaskService
        taskController = new TaskController(taskService); // Inject mock service
    }

    // Test adding a task
    @Test
    public void testAddTask() {
        Task task = new Task();
        task.setTaskName("Test Task");
        task.setStatus("Pending");
        task.setTaskID(1);

        taskController.addTask(task.getTaskName(), task.getStatus(), task.getTaskID());
        verify(taskService, times(1)).addTask(any(Task.class));
    }

    // Test retrieving a task by ID
    @Test
    public void testGetTaskById() {
        Task task = new Task();
        task.setTaskName("Sample Task");
        task.setTaskID(1);

        when(taskService.getTask(1)).thenReturn(task);
        Task result = taskController.getTaskById(1);

        assertNotNull(result);
        assertEquals("Sample Task", result.getTaskName());
    }

    // Test updating a task
    @Test
    public void testUpdateTask() {
        Task task = new Task();
        task.setTaskName("Updated Task");
        task.setTaskID(1);

        taskController.updateTask(task);
        verify(taskService, times(1)).updateTask(any(Task.class));
    }

    // Test deleting a task
    @Test
    public void testDeleteTask() {
        taskController.deleteTask(1);
        verify(taskService, times(1)).deleteTask(1);
    }

    // Test retrieving all tasks
    @Test
    public void testGetAllTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task());

        when(taskService.getAllTasks()).thenReturn(tasks);
        List<Task> result = taskController.getAllTasks();

        assertNotNull(result);
        assertEquals(1, result.size());
    }
}
