package com.cpms.cpms.services;

import com.cpms.cpms.dao.ProjectDAO;
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

public class ProjectServiceTest {

    private ProjectService projectService; // Service under test
    private ProjectDAO projectDAOMock; // Mocked DAO dependency

    @BeforeEach
    public void setUp() {
        projectDAOMock = Mockito.mock(ProjectDAO.class); // Mock the DAO
        projectService = new ProjectService(projectDAOMock); // Inject mock DAO into the service
    }

    @Test
    public void shouldAddProjectWhenProjectNameIsValid() {
        Project project = new Project();
        project.setProjectName("Valid Project");
        project.setStatus(ProjectStatus.IN_PROGRESS);

        projectService.addProject(project);

        verify(projectDAOMock, times(1)).addProject(project); // Verify DAO interaction
    }

    @Test
    public void shouldGetProjectById() {
        Project project = new Project();
        project.setProjectName("Test Project");
        project.setStatus(ProjectStatus.PLANNED);

        when(projectDAOMock.getProject(1)).thenReturn(project); // Mock DAO behavior

        Project result = projectService.getProject(1);

        assertNotNull(result);
        assertEquals("Test Project", result.getProjectName());
        assertEquals(ProjectStatus.PLANNED, result.getStatus());
        verify(projectDAOMock, times(1)).getProject(1); // Verify DAO interaction
    }

    @Test
    public void shouldUpdateProject() {
        Project project = new Project();
        project.setProjectID(1);
        project.setProjectName("Updated Project");
        project.setStatus(ProjectStatus.COMPLETED);

        projectService.updateProject(project);

        verify(projectDAOMock, times(1)).updateProject(project); // Verify DAO interaction
    }

    @Test
    public void shouldDeleteProjectById() {
        projectService.deleteProject(5);

        verify(projectDAOMock, times(1)).deleteProject(5); // Verify DAO interaction
    }

    @Test
    public void shouldGetAllProjects() {
        Project project1 = new Project();
        project1.setProjectName("Project Alpha");
        Project project2 = new Project();
        project2.setProjectName("Project Beta");

        List<Project> mockList = Arrays.asList(project1, project2);
        when(projectDAOMock.getAllProjects()).thenReturn(mockList); // Mock DAO behavior

        List<Project> result = projectService.getAllProjects();

        assertEquals(2, result.size());
        assertEquals("Project Alpha", result.get(0).getProjectName());
        assertEquals("Project Beta", result.get(1).getProjectName());
        verify(projectDAOMock, times(1)).getAllProjects(); // Verify DAO interaction
    }
}