package com.cpms.cpms.controllers;

import com.cpms.cpms.services.MilestoneService;
import com.cpms.cpms.entities.Milestone;
import com.cpms.cpms.entities.Project;
import java.sql.Date;
import java.util.List;

public class MilestoneController {
    private MilestoneService milestoneService = new MilestoneService(); // Inject MilestoneService

    // Method to add a milestone
    public void addMilestone(String name, String description, Date targetDate, Date completionDate, int projectId) {
        Milestone milestone = new Milestone();
        
        // Setting the milestone properties
        milestone.setMilestoneName(name);
        milestone.setTargetDate(targetDate);
        milestone.setCompletionDate(completionDate);
        milestone.setStatus("Pending"); // Default status, can be updated later

        // Set the associated project using the project ID
        Project project = new Project();
        project.setProjectID(projectId);
        milestone.setProject(project); // Link the milestone with the project

        milestoneService.addMilestone(milestone); // Save milestone
    }

    // Method to retrieve milestone by ID
    public Milestone getMilestoneById(int id) {
        return milestoneService.getMilestone(id); // Retrieve milestone by ID
    }

    // Method to update a milestone
    public void updateMilestone(Milestone milestone) {
        milestoneService.updateMilestone(milestone); // Update milestone
    }

    // Method to delete milestone by ID
    public void deleteMilestone(int id) {
        milestoneService.deleteMilestone(id); // Delete milestone by ID
    }

    // Method to retrieve all milestones
    public List<Milestone> getAllMilestones() {
        return milestoneService.getAllMilestones(); // Retrieve all milestones
    }
}
