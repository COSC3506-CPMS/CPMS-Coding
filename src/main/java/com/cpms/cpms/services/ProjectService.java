package com.cpms.cpms.services;

import com.cpms.cpms.dao.ProjectDAO;
import com.cpms.cpms.entities.Project;

import java.util.List;

public class ProjectService {

    private final ProjectDAO projectDAO; // DAO dependency

    // Constructor for dependency injection
    public ProjectService(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    // Adds a new project using ProjectDAO
    public void addProject(Project project) {
        projectDAO.addProject(project);
    }

    // Retrieves a project by its ID using ProjectDAO
    public Project getProject(int projectID) {
        return projectDAO.getProject(projectID);
    }

    // Updates an existing project using ProjectDAO
    public void updateProject(Project project) {
        projectDAO.updateProject(project);
    }

    // Deletes a project by its ID using ProjectDAO
    public void deleteProject(int projectID) {
        projectDAO.deleteProject(projectID);
    }

    // Retrieves all projects using ProjectDAO
    public List<Project> getAllProjects() {
        return projectDAO.getAllProjects();
    }
}