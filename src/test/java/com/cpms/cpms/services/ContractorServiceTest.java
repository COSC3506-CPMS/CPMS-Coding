package com.cpms.cpms.services;

import com.cpms.cpms.dao.ContractorDAO;
import com.cpms.cpms.entities.Contractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ContractorServiceTest {

    private ContractorService contractorService; // Service under test
    private ContractorDAO contractorDAOMock; // Mocked DAO dependency

    @BeforeEach
    public void setUp() {
        contractorDAOMock = mock(ContractorDAO.class); // Mock the DAO
        contractorService = new ContractorService(); // Create service instance

        // Inject the mocked DAO into the ContractorService instance using reflection
        try {
            java.lang.reflect.Field field = ContractorService.class.getDeclaredField("contractorDAO");
            field.setAccessible(true);
            field.set(contractorService, contractorDAOMock);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock DAO", e);
        }
    }

    @Test
    public void shouldAddContractor() {
        Contractor contractor = new Contractor();
        contractor.setContractorName("Harman Singh");
        contractor.setContactInfo("harman142@example.com");

        contractorService.addContractor(contractor);

        verify(contractorDAOMock, times(1)).addContractor(contractor); // Verify DAO interaction
    }

    @Test
    public void shouldGetContractorById() {
        Contractor mockContractor = new Contractor();
        mockContractor.setContractorName("Mina D.");
        mockContractor.setContactInfo("minaD_5@example.com");

        when(contractorDAOMock.getContractor(1)).thenReturn(mockContractor); // Mock DAO behavior

        Contractor result = contractorService.getContractor(1);

        assertNotNull(result);
        assertEquals("Mina D.", result.getContractorName());
        assertEquals("minaD_5@example.com", result.getContactInfo());
        verify(contractorDAOMock, times(1)).getContractor(1); // Verify DAO interaction
    }

    @Test
    public void shouldGetAllContractors() {
        Contractor contractor1 = new Contractor();
        contractor1.setContractorName("Olivia Brown");
        Contractor contractor2 = new Contractor();
        contractor2.setContractorName("Daniel Smith");

        List<Contractor> mockList = Arrays.asList(contractor1, contractor2);
        when(contractorDAOMock.getAllContractors()).thenReturn(mockList); // Mock DAO behavior

        List<Contractor> result = contractorService.getAllContractors();

        assertEquals(2, result.size());
        assertEquals("Olivia Brown", result.get(0).getContractorName());
        assertEquals("Daniel Smith", result.get(1).getContractorName());
        verify(contractorDAOMock, times(1)).getAllContractors(); // Verify DAO interaction
    }

    @Test
    public void shouldUpdateContractor() {
        Contractor contractor = new Contractor();
        contractor.setContractorID(1);
        contractor.setContactInfo("updated_contact@example.com");

        contractorService.updateContractor(contractor);

        verify(contractorDAOMock, times(1)).updateContractor(contractor); // Verify DAO interaction
    }

    @Test
    public void shouldDeleteContractorById() {
        contractorService.deleteContractor(5);

        verify(contractorDAOMock, times(1)).deleteContractor(5); // Verify DAO interaction
    }
}