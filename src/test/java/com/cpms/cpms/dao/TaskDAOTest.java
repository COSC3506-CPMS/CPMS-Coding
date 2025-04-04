package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Task;
import com.cpms.cpms.entities.Project;
import com.cpms.cpms.entities.Worker;

import java.sql.Date;

public class TaskDAOTest {
    public static void main(String[] args) {
        TaskDAO taskDAO = new TaskDAO();
        Project project = new Project();
        Worker worker = new Worker();

        // Test adding a task
        System.out.println("---- Testing Add Task ----");
        Task task = new Task();
        task.setTaskName("Electrical Setup");
        
        // Setting the project and worker
        project.setProjectID(1);  // Reference ProjectID from the table
        worker.setWorkerID(3);    // Reference WorkerID from the table
        
        // Set the project and worker for the task
        task.setProject(project);  // Associate the Project object
        task.setWorker(worker);    // Associate the Worker object
        
        task.setDeadline(Date.valueOf("2025-12-31"));
        task.setStatus("IN_PROGRESS");
        task.setProgressPercentage(50);

        taskDAO.addTask(task); // Add task
        System.out.println("Task added: " + task);

        // Test getting a task by ID
        System.out.println("---- Testing Get Task by ID ----");
        Task retrievedTask = taskDAO.getTask(task.getTaskID());
        if (retrievedTask != null) {
            System.out.println("Retrieved Task: " + retrievedTask);
        }

        // Test updating a task
        System.out.println("---- Testing Update Task ----");
        if (retrievedTask != null) {
            retrievedTask.setProgressPercentage(75); // Update progress
            taskDAO.updateTask(retrievedTask);
            System.out.println("Task updated: " + retrievedTask);
        }

        // Test retrieving all tasks
        System.out.println("---- Testing Get All Tasks ----");
        System.out.println("All Tasks: " + taskDAO.getAllTasks());

        // Test deleting a task
        /*System.out.println("---- Testing Delete Task ----");
        if (retrievedTask != null) {
            taskDAO.deleteTask(retrievedTask.getTaskID());
            System.out.println("Task deleted successfully!");
        }*/
    }
}
