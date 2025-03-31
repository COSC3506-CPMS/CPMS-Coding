package com.cpms.cpms.services;

import com.cpms.cpms.dao.ProjectDAO;
import com.cpms.cpms.entities.Project;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProjectServiceTest {

    private ProjectService projectService;
    private ProjectDAO projectDAOMock;

    @Before
    public void setUp() {
        projectDAOMock = Mockito.mock(ProjectDAO.class);
        projectService = new ProjectService();

        try {
            java.lang.reflect.Field daoField = ProjectService.class.getDeclaredField("projectDAO");
            daoField.setAccessible(true);
            daoField.set(projectService, projectDAOMock);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock DAO", e);
        }
    }

    @Test
    public void testAddProject() {
        Project project = new Project();
        projectService.addProject(project);
        verify(projectDAOMock).addProject(project);
    }

    @Test
    public void testGetProject() {
        Project project = new Project();
        when(projectDAOMock.getProject(1)).thenReturn(project);

        Project result = projectService.getProject(1);
        assertEquals(project, result);
    }

    @Test
    public void testUpdateProject() {
        Project project = new Project();
        projectService.updateProject(project);
        verify(projectDAOMock).updateProject(project);
    }

    @Test
    public void testDeleteProject() {
        projectService.deleteProject(7);
        verify(projectDAOMock).deleteProject(7);
    }

    @Test
    public void testGetAllProjects() {
        List<Project> mockList = Arrays.asList(new Project(), new Project());
        when(projectDAOMock.getAllProjects()).thenReturn(mockList);

        List<Project> result = projectService.getAllProjects();
        assertEquals(2, result.size());
    }
}
