package com.cpms.cpms.controllers;

import com.cpms.cpms.services.MilestoneService;
import com.cpms.cpms.entities.Milestone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MilestoneControllerTest {

    private MilestoneController milestoneController; // Controller under test
    private MilestoneService milestoneServiceMock; // Mocked MilestoneService

    @BeforeEach
    public void setUp() {
        milestoneServiceMock = Mockito.mock(MilestoneService.class); // Mock the MilestoneService
        milestoneController = new MilestoneController(); // Create an instance of the controller
        milestoneController.setMilestoneService(milestoneServiceMock); // Inject the mock into the controller
    }

    @Test
    public void testAddMilestone() {
        // Arrange: Create dummy milestone data
        String name = "Milestone 1";
        Date targetDate = Date.valueOf("2025-06-01");
        Date completionDate = Date.valueOf("2025-06-15");
        int projectId = 1;

        // Act: Call the controller method
        milestoneController.addMilestone(name, "", targetDate, completionDate, projectId);

        // Assert: Verify the service's addMilestone method was called with the correct data
        verify(milestoneServiceMock, times(1)).addMilestone(any(Milestone.class));
    }

    @Test
    public void testGetMilestoneById() {
        // Arrange: Mock the service to return a milestone
        Milestone mockMilestone = new Milestone();
        mockMilestone.setMilestoneName("Milestone 1");
        when(milestoneServiceMock.getMilestone(1)).thenReturn(mockMilestone);

        // Act: Call the controller method
        Milestone result = milestoneController.getMilestoneById(1);

        // Assert: Verify the result and service call
        assertNotNull(result);
        assertEquals("Milestone 1", result.getMilestoneName());
        verify(milestoneServiceMock, times(1)).getMilestone(1);
    }

    @Test
    public void testUpdateMilestone() {
        // Arrange: Create a dummy milestone object
        Milestone milestone = new Milestone();
        milestone.setMilestoneName("Updated Milestone");

        // Act: Call the controller method
        milestoneController.updateMilestone(milestone);

        // Assert: Verify the service's updateMilestone method was called
        verify(milestoneServiceMock, times(1)).updateMilestone(milestone);
    }

    @Test
    public void testDeleteMilestone() {
        // Act: Call the controller method
        milestoneController.deleteMilestone(5);

        // Assert: Verify the service's deleteMilestone method was called with the correct ID
        verify(milestoneServiceMock, times(1)).deleteMilestone(5);
    }

    @Test
    public void testGetAllMilestones() {
        // Arrange: Mock the service to return a list of milestones
        Milestone milestone1 = new Milestone();
        milestone1.setMilestoneName("Milestone 1");
        Milestone milestone2 = new Milestone();
        milestone2.setMilestoneName("Milestone 2");
        List<Milestone> mockMilestones = Arrays.asList(milestone1, milestone2);

        when(milestoneServiceMock.getAllMilestones()).thenReturn(mockMilestones);

        // Act: Call the controller method
        List<Milestone> result = milestoneController.getAllMilestones();

        // Assert: Verify the result and service call
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Milestone 1", result.get(0).getMilestoneName());
        assertEquals("Milestone 2", result.get(1).getMilestoneName());
        verify(milestoneServiceMock, times(1)).getAllMilestones();
    }
}
