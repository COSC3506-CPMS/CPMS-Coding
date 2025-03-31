package com.cpms.cpms.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import java.sql.Date;

@Entity
public class Project {

    @Id
    private int projectID; // Unique project ID

    @Column(nullable = false, length = 100)
    private String projectName; // Name of the project

    @Column(nullable = false)
    private Date startDate; // Start date of the project

    @Column
    private Date endDate; // End date of the project

    @Column(nullable = false, precision = 12, scale = 2)
    private double projectBudget; // Budget for the project

    @Column(nullable = false)
    private String status; // Project status: Planned, In Progress, Completed, Cancelled

    @Column
    private String description; // Description of the project

    // Getters and setters
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
