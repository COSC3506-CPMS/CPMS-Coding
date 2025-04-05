package com.cpms.cpms.controllers;

import com.cpms.cpms.entities.Milestone;
import com.cpms.cpms.services.MilestoneService;
import java.util.List;

// Controller class for managing milestone-related actions
public class MilestoneController {
    private final MilestoneService milestoneService;

    // Constructor initializes the MilestoneService
    public MilestoneController() {
        this.milestoneService = new MilestoneService();
    }

    // Adds a new milestone
    public void addMilestone(Milestone milestone) {
        milestoneService.addMilestone(milestone);
    }

    // Retrieves a milestone by its ID
    public Milestone getMilestoneById(int id) {
        return milestoneService.getMilestone(id);
    }

    // Retrieves all milestones
    public List<Milestone> getAllMilestones() {
        return milestoneService.getAllMilestones();
    }

    // Updates an existing milestone
    public void updateMilestone(Milestone milestone) {
        milestoneService.updateMilestone(milestone);
    }

    // Deletes a milestone by its ID
    public void deleteMilestone(int id) {
        milestoneService.deleteMilestone(id);
    }
}