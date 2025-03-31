package com.cpms.cpms.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.sql.Date;

@Entity
public class Milestone {

    @Id
    private int milestoneID; // Unique milestone ID

    @ManyToOne
    @JoinColumn(name = "projectID", nullable = false)
    private Project project; // Associated project

    @Column(nullable = false, length = 100)
    private String milestoneName; // Name of the milestone

    @Column
    private Date targetDate; // Planned target date

    @Column
    private Date completionDate; // Actual completion date

    @Column(nullable = false)
    private String status; // Status: Pending or Completed

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
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
