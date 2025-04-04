package com.cpms.cpms.services;

import com.cpms.cpms.dao.MilestoneDAO;
import com.cpms.cpms.entities.Milestone;
import java.util.List;

public class MilestoneService {
    private MilestoneDAO milestoneDAO;

    // Constructor to initialize DAO (optional)
    public MilestoneService() {
        this.milestoneDAO = new MilestoneDAO();  // Default DAO initialization
    }

    // Setter for MilestoneDAO to enable dependency injection
    public void setMilestoneDAO(MilestoneDAO milestoneDAO) {
        this.milestoneDAO = milestoneDAO;
    }

    // Adds a new milestone using MilestoneDAO
    public void addMilestone(Milestone milestone) {
        milestoneDAO.addMilestone(milestone);
    }

    // Retrieves a milestone by its ID using MilestoneDAO
    public Milestone getMilestone(int milestoneID) {
        return milestoneDAO.getMilestone(milestoneID);
    }

    // Updates an existing milestone using MilestoneDAO
    public void updateMilestone(Milestone milestone) {
        milestoneDAO.updateMilestone(milestone);
    }

    // Deletes a milestone by its ID using MilestoneDAO
    public void deleteMilestone(int milestoneID) {
        milestoneDAO.deleteMilestone(milestoneID);
    }

    // Retrieves all milestones using MilestoneDAO
    public List<Milestone> getAllMilestones() {
        return milestoneDAO.getAllMilestones();
    }
}
