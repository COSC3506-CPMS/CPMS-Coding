package com.cpms.cpms.entities;

import javax.persistence.*;

@Entity
@Table(name = "tasks") // Define table name
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int taskID;

    @ManyToOne
    @JoinColumn(name = "TaskProjectID", nullable = false) 
    private Project taskProjectID; // Associated Project

    @Column(nullable = false, length = 100)
    private String taskName; 

    @Column(nullable = false)
    private java.sql.Date deadline; 

    @Enumerated(EnumType.STRING) 
    @Column(name = "TaskStatus", nullable = false)
    private TaskStatus taskStatus; // Task status (enum)

    @Column
    private int progressPercentage; // Task completion percentage

    // Getters and Setters
    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public Integer getTaskProjectID() {
        return taskProjectID != null ? taskProjectID.getProjectID() : null;
    }

    public void setTaskProjectID(Project taskProjectID) {
        this.taskProjectID = taskProjectID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public java.sql.Date getDeadline() {
        return deadline;
    }

    public void setDeadline(java.sql.Date deadline) {
        this.deadline = deadline;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(int progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    // Enum for TaskStatus
    public enum TaskStatus {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }
}