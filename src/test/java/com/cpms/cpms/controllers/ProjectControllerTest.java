package com.cpms.cpms.controllers;

import com.cpms.cpms.services.ProjectService;
import com.cpms.cpms.entities.Project;
import com.cpms.cpms.entities.Project.ProjectStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectControllerTest {

    private ProjectController projectController; // Controller under test
    private ProjectService projectServiceMock; // Mocked ProjectService

    @BeforeEach
    public void setUp() {
        projectServiceMock = Mockito.mock(ProjectService.class); // Mock the ProjectService
        projectController = new ProjectController(projectServiceMock); // Inject the mock into the controller
    }

    @Test
    public void testAddProject() {
        // Arrange: Create dummy project data
        String name = "Test Project";
        String description = "Test Description";
        Date startDate = Date.valueOf("2025-04-01");
        Date endDate = Date.valueOf("2025-12-31");
        double budget = 500000.00;
        String status = "IN_PROGRESS";

        // Act: Call the controller method
        projectController.addProject(name, description, startDate, endDate, budget, status);

        // Assert: Verify the service's addProject method was called with the correct data
        verify(projectServiceMock, times(1)).addProject(any(Project.class));
    }

    @Test
    public void testGetProjectById() {
        // Arrange: Mock the service to return a project
        Project mockProject = new Project();
        mockProject.setProjectName("Test Project");
        mockProject.setStatus(ProjectStatus.PLANNED);
        when(projectServiceMock.getProject(1)).thenReturn(mockProject);

        // Act: Call the controller method
        Project result = projectController.getProjectById(1);

        // Assert: Verify the result and service call
        assertNotNull(result);
        assertEquals("Test Project", result.getProjectName());
        assertEquals(ProjectStatus.PLANNED, result.getStatus());
        verify(projectServiceMock, times(1)).getProject(1);
    }

    @Test
    public void testUpdateProject() {
        // Arrange: Create a dummy project object
        Project project = new Project();
        project.setProjectID(1);
        project.setProjectName("Updated Project");
        project.setStatus(ProjectStatus.COMPLETED);

        // Act: Call the controller method
        projectController.updateProject(project);

        // Assert: Verify the service's updateProject method was called
        verify(projectServiceMock, times(1)).updateProject(project);
    }

    @Test
    public void testDeleteProject() {
        // Act: Call the controller method
        projectController.deleteProject(5);

        // Assert: Verify the service's deleteProject method was called with the correct ID
        verify(projectServiceMock, times(1)).deleteProject(5);
    }

    @Test
    public void testGetAllProjects() {
        // Arrange: Mock the service to return a list of projects
        Project project1 = new Project();
        project1.setProjectName("Project Alpha");
        Project project2 = new Project();
        project2.setProjectName("Project Beta");
        List<Project> mockProjects = Arrays.asList(project1, project2);

        when(projectServiceMock.getAllProjects()).thenReturn(mockProjects);

        // Act: Call the controller method
        List<Project> result = projectController.getAllProjects();

        // Assert: Verify the result and service call
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Project Alpha", result.get(0).getProjectName());
        assertEquals("Project Beta", result.get(1).getProjectName());
        verify(projectServiceMock, times(1)).getAllProjects();
    }
}