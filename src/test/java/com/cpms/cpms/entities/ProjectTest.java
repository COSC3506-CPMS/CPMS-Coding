package com.cpms.cpms.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.Date;

public class ProjectTest {

    @Test
    public void testProjectSettersAndGetters() {
        // Arrange
        Project project = new Project();
        int projectID = 101;
        String projectName = "New System Launch";
        Date startDate = Date.valueOf("2025-04-01");
        Date endDate = Date.valueOf("2025-12-31");
        double budget = 500000.00;
        Project.ProjectStatus status = Project.ProjectStatus.IN_PROGRESS; // Using enum for status
        String description = "Launching new enterprise software system";

        // Act
        project.setProjectID(projectID);
        project.setProjectName(projectName);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setProjectBudget(budget);
        project.setStatus(status); // Updated to use enum value
        project.setDescription(description);

        // Assert
        Assertions.assertEquals(projectID, project.getProjectID());
        Assertions.assertEquals(projectName, project.getProjectName());
        Assertions.assertEquals(startDate, project.getStartDate());
        Assertions.assertEquals(endDate, project.getEndDate());
        Assertions.assertEquals(budget, project.getProjectBudget(), 0.001);
        Assertions.assertEquals(status, project.getStatus()); // Validating enum value
        Assertions.assertEquals(description, project.getDescription());
    }
}