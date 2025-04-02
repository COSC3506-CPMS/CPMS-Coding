package com.cpms.cpms.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "projects") 
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private int projectID;

    @Column(nullable = false, length = 100)
    private String projectName; // Name of the project

    @Column(nullable = false)
    private Date startDate; // Start date of the project

    @Column
    private Date endDate; // End date of the project

    @Column(nullable = false, precision = 12, scale = 2)
    private double projectBudget; // Budget for the project

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status; // Project status as an enum

    @Column(columnDefinition = "TEXT") // Matches the database text type
    private String description; // Description of the project

    // Enum for project status
    public enum ProjectStatus {
        PLANNED,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }

    // Getters and Setters
    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getProjectBudget() {
        return projectBudget;
    }

    public void setProjectBudget(double projectBudget) {
        this.projectBudget = projectBudget;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}