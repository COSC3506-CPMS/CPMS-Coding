package com.cpms.cpms.controllers;

import com.cpms.cpms.services.ProjectService;
import com.cpms.cpms.entities.Project;

import java.sql.Date;
import java.util.List;

public class ProjectController {

    private final ProjectService projectService; // Service dependency

    // Constructor for dependency injection
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Adds a new project
    public void addProject(String name, String description, Date startDate, Date endDate, double budget, String status) {
        Project project = new Project();
        project.setProjectName(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setProjectBudget(budget);
        project.setStatus(Project.ProjectStatus.valueOf(status.toUpperCase())); // Convert status to enum
        projectService.addProject(project); // Save project
    }

    // Retrieves a project by its ID
    public Project getProjectById(int id) {
        return projectService.getProject(id); // Retrieve project by ID
    }

    // Updates an existing project
    public void updateProject(Project project) {
        projectService.updateProject(project); // Update project
    }

    // Deletes a project by its ID
    public void deleteProject(int id) {
        projectService.deleteProject(id); // Delete project by ID
    }

    // Retrieves all projects
    public List<Project> getAllProjects() {
        return projectService.getAllProjects(); // Retrieve all projects
    }
}