// Entity class representing milestones in the database
package com.cpms.cpms.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "milestones")
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MilestoneID")
    private int milestoneID;

    @ManyToOne
    @JoinColumn(name = "MilestoneProjectID", nullable = false)
    private Project mProjectID;

    @Column(name = "MilestoneName", nullable = false, length = 100)
    private String milestoneName;

    @Column(name = "TargetDate")
    private Date targetDate;

    @Column(name = "CompletionDate")
    private Date completionDate;

    @Column(name = "MilestoneStatus", nullable = false)
    private String milestoneStatus;

    // Getters and setters
    public int getMilestoneID() {
        return milestoneID;
    }

    public void setMilestoneID(int milestoneID) {
        this.milestoneID = milestoneID;
    }

    public Integer getMProjectID() {
        return mProjectID != null ? mProjectID.getProjectID() : null;
    }

    public void setMProjectID(Project mProjectID) {
        this.mProjectID = mProjectID;
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

    public String getMilestoneStatus() {
        return milestoneStatus;
    }

    public void setMilestoneStatus(String milestoneStatus) {
        this.milestoneStatus = milestoneStatus;
    }

    public enum MilestoneStatus {
        PENDING,
        COMPLETED
    }
}