// TaskController.java
package com.cpms.cpms.controllers;

import com.cpms.cpms.services.TaskService;
import com.cpms.cpms.entities.Task;
import java.util.List;

// Controller class to manage tasks
public class TaskController {
    private TaskService taskService; // Service to handle task operations

    // Constructor to inject TaskService
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Adds a new task with given details
    public void addTask(String name, String status, int taskId) {
        Task task = new Task();
        task.setTaskName(name);
        task.setStatus(status);
        task.setTaskID(taskId);
        taskService.addTask(task); // Calls service method to add task
    }

    // Retrieves a task by its ID
    public Task getTaskById(int id) {
        return taskService.getTask(id); // Calls service method to get task
    }

    // Updates an existing task
    public void updateTask(Task task) {
        taskService.updateTask(task); // Calls service method to update task
    }

    // Deletes a task by its ID
    public void deleteTask(int id) {
        taskService.deleteTask(id); // Calls service method to delete task
    }

    // Retrieves all tasks
    public List<Task> getAllTasks() {
        return taskService.getAllTasks(); // Calls service method to get all tasks
    }
}
