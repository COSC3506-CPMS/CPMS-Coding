package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Project;

import java.sql.Date;

public class ProjectDAOTest {
    public static void main(String[] args) {
        ProjectDAO projectDAO = new ProjectDAO();

        // Test adding a project
        System.out.println("---- Testing Add Project ----");
        Project project = new Project();
        project.setProjectName("New System Launch"); // Project Name
        project.setStartDate(Date.valueOf("2025-04-01")); // Start Date
        project.setEndDate(Date.valueOf("2025-12-31")); // End Date
        project.setProjectBudget(500000.00); // Budget
        project.setStatus(Project.ProjectStatus.IN_PROGRESS); // Status Enum
        project.setDescription("Launching a new enterprise software system"); // Description

        projectDAO.addProject(project); // Add project
        System.out.println("Project added: " + project);

        // Test getting a project by ID
        System.out.println("---- Testing Get Project by ID ----");
        Project retrievedProject = projectDAO.getProject(project.getProjectID()); // Get Project ID dynamically
        if (retrievedProject != null) {
            System.out.println("Retrieved Project: " + retrievedProject);
        }

        // Test updating a project
        System.out.println("---- Testing Update Project ----");
        if (retrievedProject != null) {
            retrievedProject.setProjectBudget(550000.00); // Update budget
            projectDAO.updateProject(retrievedProject);
            System.out.println("Project updated: " + retrievedProject);
        }

        // Test retrieving all projects
        System.out.println("---- Testing Get All Projects ----");
        System.out.println("All Projects: " + projectDAO.getAllProjects());

        // Test deleting a project
        System.out.println("---- Testing Delete Project ----");
        if (retrievedProject != null) {
            projectDAO.deleteProject(retrievedProject.getProjectID());
            System.out.println("Project deleted successfully!");
        }
    }
}