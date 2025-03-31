package com.cpms.cpms.controllers;

import com.cpms.cpms.services.ProjectService;
import com.cpms.cpms.entities.Project;
import java.util.List;

public class ProjectController {
    private ProjectService projectService = new ProjectService(); // Inject ProjectService

    public void addProject(String name, String description, java.sql.Date startDate, java.sql.Date endDate) {
        Project project = new Project();
        project.setProjectName(name);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        projectService.addProject(project); // Save project
    }

    public Project getProjectById(int id) {
        return projectService.getProject(id); // Retrieve project by ID
    }

    public void updateProject(Project project) {
        projectService.updateProject(project); // Update project
    }

    public void deleteProject(int id) {
        projectService.deleteProject(id); // Delete project by ID
    }

    public List<Project> getAllProjects() {
        return projectService.getAllProjects(); // Retrieve all projects
    }
}
