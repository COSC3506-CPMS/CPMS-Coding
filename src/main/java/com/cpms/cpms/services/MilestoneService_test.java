package com.cpms.cpms.services;

import com.cpms.cpms.dao.MilestoneDAO;
import com.cpms.cpms.entities.Milestone;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MilestoneServiceTest {

    private MilestoneService milestoneService;
    private MilestoneDAO milestoneDAOMock;

    @Before
    public void setUp() {
        milestoneDAOMock = Mockito.mock(MilestoneDAO.class);
        milestoneService = new MilestoneService();

        try {
            java.lang.reflect.Field daoField = MilestoneService.class.getDeclaredField("milestoneDAO");
            daoField.setAccessible(true);
            daoField.set(milestoneService, milestoneDAOMock);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock DAO", e);
        }
    }

    @Test
    public void testAddMilestone() {
        Milestone milestone = new Milestone();
        milestoneService.addMilestone(milestone);
        verify(milestoneDAOMock).addMilestone(milestone);
    }

    @Test
    public void testGetMilestone() {
        Milestone milestone = new Milestone();
        when(milestoneDAOMock.getMilestone(1)).thenReturn(milestone);

        Milestone result = milestoneService.getMilestone(1);
        assertEquals(milestone, result);
    }

    @Test
    public void testUpdateMilestone() {
        Milestone milestone = new Milestone();
        milestoneService.updateMilestone(milestone);
        verify(milestoneDAOMock).updateMilestone(milestone);
    }

    @Test
    public void testDeleteMilestone() {
        milestoneService.deleteMilestone(99);
        verify(milestoneDAOMock).deleteMilestone(99);
    }

    @Test
    public void testGetAllMilestones() {
        List<Milestone> mockList = Arrays.asList(new Milestone(), new Milestone());
        when(milestoneDAOMock.getAllMilestones()).thenReturn(mockList);

        List<Milestone> result = milestoneService.getAllMilestones();
        assertEquals(2, result.size());
    }
}
