package com.cpms.cpms.controllers;

import com.cpms.cpms.services.TaskService;
import com.cpms.cpms.entities.Task;
import java.util.List;

public class TaskController {
    private TaskService taskService = new TaskService(); // Inject TaskService

    public void addTask(String name, String description, String status, int taskId) {
        Task task = new Task();
        task.setTaskName(name);

        // Convert the status string to the TaskStatus enum and set it
        try {
            Task.TaskStatus taskStatusEnum = Task.TaskStatus.valueOf(status.toUpperCase());
            task.setTaskStatus(taskStatusEnum); // Set TaskStatus using the enum
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid task status provided. Accepted values: PENDING, IN_PROGRESS, COMPLETED.", e);
        }

        task.setTaskID(taskId);

        // Save the task using the TaskService
        taskService.addTask(task);
    }
    public Task getTaskById(int id) {
        return taskService.getTask(id); // Retrieve task by ID
    }

    public void updateTask(Task task) {
        taskService.updateTask(task); // Update task
    }

    public void deleteTask(int id) {
        taskService.deleteTask(id); // Delete task by ID
    }

    public List<Task> getAllTasks() {
        return taskService.getAllTasks(); // Retrieve all tasks
    }
}

