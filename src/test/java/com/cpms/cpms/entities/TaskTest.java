package com.cpms.cpms.entities;

import org.junit.jupiter.api.Test; // Using JUnit 5 for testing
import org.junit.jupiter.api.Assertions; // Updated import for assertions

public class TaskTest {

    @Test
    public void testTaskSettersAndGetters() {
        // Create a new Task object
        Task task = new Task();

        // Values to test
        int taskID = 101;
        String taskName = "Develop Feature";
        java.sql.Date deadline = java.sql.Date.valueOf("2025-04-30");
        String status = "Pending";
        int progressPercentage = 50;

        // Set values using setter methods
        task.setTaskID(taskID);
        task.setTaskName(taskName);
        task.setDeadline(deadline);
        task.setStatus(status);
        task.setProgressPercentage(progressPercentage);

        // Validate values using getter methods and assertions
        Assertions.assertEquals(taskID, task.getTaskID());
        Assertions.assertEquals(taskName, task.getTaskName());
        Assertions.assertEquals(deadline, task.getDeadline());
        Assertions.assertEquals(status, task.getStatus());
        Assertions.assertEquals(progressPercentage, task.getProgressPercentage());
    }
}