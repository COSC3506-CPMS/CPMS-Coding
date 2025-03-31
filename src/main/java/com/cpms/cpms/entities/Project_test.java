package com.cpms.cpms.entities;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;

public class ProjectTest {

    @Test
    public void testProjectSettersAndGetters() {
        Project project = new Project();

        int projectID = 101;
        String projectName = "New System Launch";
        Date startDate = Date.valueOf("2025-04-01");
        Date endDate = Date.valueOf("2025-12-31");
        double budget = 500000.00;
        String status = "In Progress";
        String description = "Launching new enterprise software system";
      
        project.setProjectID(projectID);
        project.setProjectName(projectName);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setProjectBudget(budget);
        project.setStatus(status);
        project.setDescription(description);
      
        Assert.assertEquals(projectID, project.getProjectID());
        Assert.assertEquals(projectName, project.getProjectName());
        Assert.assertEquals(startDate, project.getStartDate());
        Assert.assertEquals(endDate, project.getEndDate());
        Assert.assertEquals(budget, project.getProjectBudget(), 0.001);
        Assert.assertEquals(status, project.getStatus());
        Assert.assertEquals(description, project.getDescription());
    }
}
