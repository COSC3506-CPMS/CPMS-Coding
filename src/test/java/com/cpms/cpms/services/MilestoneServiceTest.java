package com.cpms.cpms.services;

import com.cpms.cpms.dao.MilestoneDAO;
import com.cpms.cpms.entities.Milestone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MilestoneServiceTest {

    private MilestoneService milestoneService;
    private MilestoneDAO milestoneDAO;

    @BeforeEach
    void setUp() {
        milestoneDAO = mock(MilestoneDAO.class); // Mocking MilestoneDAO
        milestoneService = new MilestoneService();
        milestoneService.setMilestoneDAO(milestoneDAO); // Injecting mocked DAO into service
    }

    @Test
    void testAddMilestone() {
        Milestone milestone = new Milestone();
        milestone.setMilestoneName("Test Milestone");

        milestoneService.addMilestone(milestone);

        verify(milestoneDAO, times(1)).addMilestone(milestone);
    }

    @Test
    void testGetMilestone() {
        Milestone milestone = new Milestone();
        milestone.setMilestoneID(1);
        milestone.setMilestoneName("Test Milestone");

        when(milestoneDAO.getMilestone(1)).thenReturn(milestone);

        Milestone fetchedMilestone = milestoneService.getMilestone(1);

        assertEquals(1, fetchedMilestone.getMilestoneID());
        assertEquals("Test Milestone", fetchedMilestone.getMilestoneName());
        verify(milestoneDAO, times(1)).getMilestone(1);
    }

    @Test
    void testUpdateMilestone() {
        Milestone milestone = new Milestone();
        milestone.setMilestoneID(1);
        milestone.setMilestoneName("Updated Milestone");

        milestoneService.updateMilestone(milestone);

        verify(milestoneDAO, times(1)).updateMilestone(milestone);
    }

    @Test
    void testDeleteMilestone() {
        milestoneService.deleteMilestone(1);

        verify(milestoneDAO, times(1)).deleteMilestone(1);
    }

    @Test
    void testGetAllMilestones() {
        List<Milestone> milestones = new ArrayList<>();
        Milestone milestone1 = new Milestone();
        milestone1.setMilestoneName("Milestone 1");
        Milestone milestone2 = new Milestone();
        milestone2.setMilestoneName("Milestone 2");
        milestones.add(milestone1);
        milestones.add(milestone2);

        when(milestoneDAO.getAllMilestones()).thenReturn(milestones);

        List<Milestone> fetchedMilestones = milestoneService.getAllMilestones();

        assertEquals(2, fetchedMilestones.size());
        assertEquals("Milestone 1", fetchedMilestones.get(0).getMilestoneName());
        assertEquals("Milestone 2", fetchedMilestones.get(1).getMilestoneName());
        verify(milestoneDAO, times(1)).getAllMilestones();
    }
}