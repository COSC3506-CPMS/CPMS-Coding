package com.cpms.cpms.controllers;

import com.cpms.cpms.entities.Contractor;
import com.cpms.cpms.services.ContractorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ContractorControllerTest {

    private ContractorController contractorController; // Controller under test
    private ContractorService contractorServiceMock; // Mocked service dependency

    @BeforeEach
    public void setUp() {
        contractorServiceMock = mock(ContractorService.class); // Mock the ContractorService

        // Correctly inject mocked service into ContractorController
        contractorController = new ContractorController(contractorServiceMock);
    }

    @Test
    public void shouldAddContractor() {
        String contractorName = "Harman Singh";
        String contactInfo = "harman142@example.com";

        contractorController.addContractor(contractorName, contactInfo);

        verify(contractorServiceMock, times(1)).addContractor(any(Contractor.class));
    }

    @Test
    public void shouldGetContractorById() {
        Contractor mockContractor = new Contractor();
        mockContractor.setContractorName("Mina D.");
        mockContractor.setContactInfo("minaD_5@example.com");

        when(contractorServiceMock.getContractor(1)).thenReturn(mockContractor);

        Contractor result = contractorController.getContractorById(1);

        assertNotNull(result);
        assertEquals("Mina D.", result.getContractorName());
        assertEquals("minaD_5@example.com", result.getContactInfo());
        verify(contractorServiceMock, times(1)).getContractor(1);
    }

    @Test
    public void shouldGetAllContractors() {
        Contractor contractor1 = new Contractor();
        contractor1.setContractorName("Olivia Brown");
        Contractor contractor2 = new Contractor();
        contractor2.setContractorName("Daniel Smith");

        List<Contractor> mockList = Arrays.asList(contractor1, contractor2);
        when(contractorServiceMock.getAllContractors()).thenReturn(mockList);

        List<Contractor> result = contractorController.getAllContractors();

        assertEquals(2, result.size());
        assertEquals("Olivia Brown", result.get(0).getContractorName());
        assertEquals("Daniel Smith", result.get(1).getContractorName());
        verify(contractorServiceMock, times(1)).getAllContractors();
    }

    @Test
    public void shouldUpdateContractor() {
        Contractor contractor = new Contractor();
        contractor.setContractorID(1);
        contractor.setContactInfo("updated_contact@example.com");

        contractorController.updateContractor(contractor);

        verify(contractorServiceMock, times(1)).updateContractor(contractor);
    }

    @Test
    public void shouldDeleteContractorById() {
        contractorController.deleteContractor(5);

        verify(contractorServiceMock, times(1)).deleteContractor(5);
    }
}