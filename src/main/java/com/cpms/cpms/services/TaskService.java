package com.cpms.cpms.services;

import com.cpms.cpms.dao.TaskDAO;
import com.cpms.cpms.entities.Task;
import java.util.List;

public class TaskService {
    private TaskDAO taskDAO = new TaskDAO();

    // Adds a new task using TaskDAO
    public void addTask(Task task) {
        taskDAO.addTask(task);
    }

    // Retrieves a task by its ID using TaskDAO
    public Task getTask(int taskID) {
        return taskDAO.getTask(taskID);
    }

    // Updates an existing task using TaskDAO
    public void updateTask(Task task) {
        taskDAO.updateTask(task);
    }

    // Deletes a task by its ID using TaskDAO
    public void deleteTask(int taskID) {
        taskDAO.deleteTask(taskID);
    }

    // Retrieves all tasks using TaskDAO
    public List<Task> getAllTasks() {
        return taskDAO.getAllTasks();
    }
}
