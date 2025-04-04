package com.cpms.cpms.entities;

import javax.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "milestones") // Specify table name as 'milestones'
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment for MilestoneID
    @Column(name = "MilestoneID") // Ensure the column name matches the table column
    private int milestoneID; // Unique milestone ID (auto-incremented)

    @ManyToOne
    @JoinColumn(name = "ProjectID", nullable = false) // Foreign key to Project table
    private Project project; // Associated project

    @Column(name = "MilestoneName", nullable = false, length = 100) // Column for milestone name
    private String milestoneName; // Name of the milestone

    @Column(name = "TargetDate") // Column for the target date
    private Date targetDate; // Planned target date

    @Column(name = "CompletionDate") // Column for the completion date
    private Date completionDate; // Actual completion date

    @Column(name = "MilestoneStatus", nullable = false) // Column for status (Pending or Completed)
    private String milestoneStatus; // Status: Pending or Completed

    // Getters and setters

    public int getMilestoneID() {
        return milestoneID;
    }

    public void setMilestoneID(int milestoneID) {
        this.milestoneID = milestoneID;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public String getStatus() {
        return milestoneStatus;
    }

    public void setStatus(String milestoneStatus) {
        this.milestoneStatus = milestoneStatus;
    }
    
    public enum MilestoneStatus {
        PENDING,
        COMPLETED;
    }
}
