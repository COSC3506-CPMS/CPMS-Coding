package com.cpms.cpms.services;

import com.cpms.cpms.dao.ContractorDAO;
import com.cpms.cpms.entities.Contractor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ContractorServiceTest {

    private ContractorService contractorService;
    private ContractorDAO contractorDAOMock;

    @Before
    public void setUp() {
        contractorDAOMock = Mockito.mock(ContractorDAO.class);
        contractorService = new ContractorService();

        try {
            java.lang.reflect.Field daoField = ContractorService.class.getDeclaredField("contractorDAO");
            daoField.setAccessible(true);
            daoField.set(contractorService, contractorDAOMock);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock DAO", e);
        }
    }

    @Test
    public void testAddContractor() {
        Contractor contractor = new Contractor();
        contractorService.addContractor(contractor);
        verify(contractorDAOMock).addContractor(contractor);
    }

    @Test
    public void testGetContractor() {
        Contractor contractor = new Contractor();
        when(contractorDAOMock.getContractor(1)).thenReturn(contractor);

        Contractor result = contractorService.getContractor(1);
        assertEquals(contractor, result);
    }

    @Test
    public void testUpdateContractor() {
        Contractor contractor = new Contractor();
        contractorService.updateContractor(contractor);
        verify(contractorDAOMock).updateContractor(contractor);
    }

    @Test
    public void testDeleteContractor() {
        contractorService.deleteContractor(5);
        verify(contractorDAOMock).deleteContractor(5);
    }

    @Test
    public void testGetAllContractors() {
        List<Contractor> mockList = Arrays.asList(new Contractor(), new Contractor());
        when(contractorDAOMock.getAllContractors()).thenReturn(mockList);

        List<Contractor> result = contractorService.getAllContractors();
        assertEquals(2, result.size());
    }
}
