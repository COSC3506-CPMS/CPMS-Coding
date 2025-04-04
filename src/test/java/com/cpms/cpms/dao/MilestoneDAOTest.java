package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Milestone;
import com.cpms.cpms.entities.Project;

public class MilestoneDAOTest {

    public static void main(String[] args) {
        MilestoneDAO milestoneDAO = new MilestoneDAO();
        ProjectDAO projectDAO = new ProjectDAO(); // Assuming you have a ProjectDAO class

        // Test adding a milestone
        System.out.println("---- Testing Add Milestone ----");
        Project project = projectDAO.getProject(1); // Get a project by ID (ensure the project exists)
        if (project != null) {
            Milestone milestone = new Milestone();
            milestone.setProject(project);  // Set the associated project
            milestone.setMilestoneName("Initial Setup");
            milestone.setTargetDate(java.sql.Date.valueOf("2025-05-01"));
            milestone.setStatus("Pending");

            milestoneDAO.addMilestone(milestone); // Add milestone
            System.out.println("Milestone added: " + milestone);
        } else {
            System.out.println("Project not found!");
        }

        // Test getting a milestone by ID
        System.out.println("---- Testing Get Milestone by ID ----");
        Milestone retrievedMilestone = milestoneDAO.getMilestone(1); // Replace with a valid MilestoneID
        if (retrievedMilestone != null) {
            System.out.println("Retrieved Milestone: " + retrievedMilestone);
        } else {
            System.out.println("Milestone not found!");
        }

        // Test updating a milestone
        System.out.println("---- Testing Update Milestone ----");
        if (retrievedMilestone != null) {
            retrievedMilestone.setStatus("Completed");
            milestoneDAO.updateMilestone(retrievedMilestone);
            System.out.println("Milestone updated: " + retrievedMilestone);
        }

        // Test retrieving all milestones
        System.out.println("---- Testing Get All Milestones ----");
        System.out.println("All Milestones: " + milestoneDAO.getAllMilestones());

        // Test deleting a milestone
        /*System.out.println("---- Testing Delete Milestone ----");
        if (retrievedMilestone != null) {
            milestoneDAO.deleteMilestone(retrievedMilestone.getMilestoneID());
            System.out.println("Milestone deleted successfully!");
        }*/
    }
}
