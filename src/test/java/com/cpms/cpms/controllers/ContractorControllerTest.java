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
        contractorController = new ContractorController();

        // Inject the mocked service into the ContractorController instance using reflection
        try {
            java.lang.reflect.Field field = ContractorController.class.getDeclaredField("contractorService");
            field.setAccessible(true);
            field.set(contractorController, contractorServiceMock);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock service", e);
        }
    }

    @Test
    public void shouldAddContractor() {
        // Arrange: Mock input data
        String contractorName = "Harman Singh";
        String contactInfo = "harman142@example.com";

        // Act: Call the addContractor method
        contractorController.addContractor(contractorName, null, contactInfo);

        // Assert: Verify the contractorService interaction
        verify(contractorServiceMock, times(1)).addContractor(any(Contractor.class)); // Verify service interaction
    }

    @Test
    public void shouldGetContractorById() {
        // Arrange: Mock a contractor
        Contractor mockContractor = new Contractor();
        mockContractor.setContractorName("Mina D.");
        mockContractor.setContactInfo("minaD_5@example.com");

        when(contractorServiceMock.getContractor(1)).thenReturn(mockContractor); // Mock service behavior

        // Act: Call the getContractorById method
        Contractor result = contractorController.getContractorById(1);

        // Assert: Validate the result and verify service interaction
        assertNotNull(result);
        assertEquals("Mina D.", result.getContractorName());
        assertEquals("minaD_5@example.com", result.getContactInfo());
        verify(contractorServiceMock, times(1)).getContractor(1); // Verify service interaction
    }

    @Test
    public void shouldGetAllContractors() {
        // Arrange: Mock a list of contractors
        Contractor contractor1 = new Contractor();
        contractor1.setContractorName("Olivia Brown");
        Contractor contractor2 = new Contractor();
        contractor2.setContractorName("Daniel Smith");

        List<Contractor> mockList = Arrays.asList(contractor1, contractor2);
        when(contractorServiceMock.getAllContractors()).thenReturn(mockList); // Mock service behavior

        // Act: Call the getAllContractors method
        List<Contractor> result = contractorController.getAllContractors();

        // Assert: Validate the result and verify service interaction
        assertEquals(2, result.size());
        assertEquals("Olivia Brown", result.get(0).getContractorName());
        assertEquals("Daniel Smith", result.get(1).getContractorName());
        verify(contractorServiceMock, times(1)).getAllContractors(); // Verify service interaction
    }

    @Test
    public void shouldUpdateContractor() {
        // Arrange: Mock a contractor to update
        Contractor contractor = new Contractor();
        contractor.setContractorID(1);
        contractor.setContactInfo("updated_contact@example.com");

        // Act: Call the updateContractor method
        contractorController.updateContractor(contractor);

        // Assert: Verify service interaction
        verify(contractorServiceMock, times(1)).updateContractor(contractor); // Verify service interaction
    }

    @Test
    public void shouldDeleteContractorById() {
        // Act: Call the deleteContractor method
        contractorController.deleteContractor(5);

        // Assert: Verify service interaction
        verify(contractorServiceMock, times(1)).deleteContractor(5); // Verify service interaction
    }
}