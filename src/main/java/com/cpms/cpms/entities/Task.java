package com.cpms.cpms.entities;

import javax.persistence.*;
import com.cpms.cpms.entities.*;
@Entity
@Table(name = "tasks") // Specify table name
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment task ID
	private int taskID;
   
	@ManyToOne
	@JoinColumn(name = "TaskProjectID", nullable = false) 
	private Project taskProjectID; // Associated Project
	
	@Column(nullable = false, length = 100)
	private String taskName; // Name of the task
	
	@Column
	private java.sql.Date deadline; // Task deadline
	
	@Column(name = "TaskStatus", nullable = false)
	private String taskStatus; // Task status: Pending, In Progress, Completed
	
	@Column
	private int progressPercentage; // Task completion percentage
   
   
	// Getters and Setters
	public int getTaskID() {
		return taskID;
	}
	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}
	
	//foreign keys
	//project id
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
	
	public String getStatus() {
		return taskStatus;
	}
	
	public void setStatus(String status) {
		this.taskStatus = status;
	}
	
	public int getProgressPercentage() {
		return progressPercentage;
	}
	
	public void setProgressPercentage(int progressPercentage) {
		this.progressPercentage = progressPercentage;
	}
}
