package com.cpms.cpms.entities;
import javax.persistence.*;
@Entity
@Table(name = "tasks") // Specify table name
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment task ID
	private int taskID;
   
	@ManyToOne
	@JoinColumn(name = "projectID", nullable = false)
	private Project project; // Associated project
	
	@ManyToOne
	@JoinColumn(name = "workerID")
	private Worker worker; // Assigned worker (optional)
	
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
	
	public Project getProject() {
		return project;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public Worker getWorker() {
		return worker;
	}
	
	public void setWorker(Worker worker) {
		this.worker = worker;
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
