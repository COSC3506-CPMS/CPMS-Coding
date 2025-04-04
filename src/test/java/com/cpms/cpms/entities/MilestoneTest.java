package com.cpms.cpms.entities;

import org.junit.jupiter.api.Test; // Using JUnit 5 for testing
import org.junit.jupiter.api.Assertions; // Updated import for assertions
import java.sql.Date;

public class MilestoneTest {

    @Test
    public void testMilestoneSettersAndGetters() {
        // Create a new Milestone object
        Milestone milestone = new Milestone();

        // Values to test
        int milestoneID = 1;
        Project project = new Project(); // Assume Project class is defined
        String milestoneName = "Phase 1 Completion";
        Date targetDate = Date.valueOf("2025-06-30");
        Date completionDate = Date.valueOf("2025-07-01");
        String status = "Completed";

        // Set values using setter methods
        milestone.setMilestoneID(milestoneID);
        milestone.setProject(project);
        milestone.setMilestoneName(milestoneName);
        milestone.setTargetDate(targetDate);
        milestone.setCompletionDate(completionDate);
        milestone.setStatus(status);

        // Validate values using getter methods and assertions
        Assertions.assertEquals(milestoneID, milestone.getMilestoneID());
        Assertions.assertEquals(project, milestone.getProject());
        Assertions.assertEquals(milestoneName, milestone.getMilestoneName());
        Assertions.assertEquals(targetDate, milestone.getTargetDate());
        Assertions.assertEquals(completionDate, milestone.getCompletionDate());
        Assertions.assertEquals(status, milestone.getStatus());
    }
}
