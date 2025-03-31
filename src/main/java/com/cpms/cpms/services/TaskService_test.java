package com.cpms.cpms.services;

import com.cpms.cpms.dao.TaskDAO;
import com.cpms.cpms.entities.Task;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    private TaskService taskService;
    private TaskDAO taskDAOMock;

    @Before
    public void setUp() {
        taskDAOMock = Mockito.mock(TaskDAO.class);
        taskService = new TaskService();

        try {
            java.lang.reflect.Field daoField = TaskService.class.getDeclaredField("taskDAO");
            daoField.setAccessible(true);
            daoField.set(taskService, taskDAOMock);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock DAO", e);
        }
    }

    @Test
    public void testAddTask() {
        Task task = new Task();
        taskService.addTask(task);
        verify(taskDAOMock).addTask(task);
    }

    @Test
    public void testGetTask() {
        Task task = new Task();
        when(taskDAOMock.getTask(1)).thenReturn(task);

        Task result = taskService.getTask(1);
        assertEquals(task, result);
    }

    @Test
    public void testUpdateTask() {
        Task task = new Task();
        taskService.updateTask(task);
        verify(taskDAOMock).updateTask(task);
    }

    @Test
    public void testDeleteTask() {
        taskService.deleteTask(11);
        verify(taskDAOMock).deleteTask(11);
    }

    @Test
    public void testGetAllTasks() {
        List<Task> mockList = Arrays.asList(new Task(), new Task());
        when(taskDAOMock.getAllTasks()).thenReturn(mockList);

        List<Task> result = taskService.getAllTasks();
        assertEquals(2, result.size());
    }
}
