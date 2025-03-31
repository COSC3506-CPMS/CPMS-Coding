package com.cpms.cpms.entities;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;

public class MilestoneTest {

    @Test
    public void testMilestoneSettersAndGetters() {
        Milestone milestone = new Milestone();

        int milestoneID = 10;
        String milestoneName = "Design Phase";
        String status = "Pending";

        Date targetDate = Date.valueOf("2025-06-01");
        Date completionDate = Date.valueOf("2025-06-15");

        Project testProject = new Project();
        testProject.setProjectID(100);  

        milestone.setMilestoneID(milestoneID);
        milestone.setMilestoneName(milestoneName);
        milestone.setStatus(status);
        milestone.setTargetDate(targetDate);
        milestone.setCompletionDate(completionDate);
        milestone.setProject(testProject);

        Assert.assertEquals(milestoneID, milestone.getMilestoneID());
        Assert.assertEquals(milestoneName, milestone.getMilestoneName());
        Assert.assertEquals(status, milestone.getStatus());
        Assert.assertEquals(targetDate, milestone.getTargetDate());
        Assert.assertEquals(completionDate, milestone.getCompletionDate());
        Assert.assertEquals(testProject, milestone.getProject());
    }
}
