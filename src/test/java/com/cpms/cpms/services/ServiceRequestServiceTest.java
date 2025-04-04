package com.cpms.cpms.services;

import com.cpms.cpms.dao.ServiceRequestDAO;
import com.cpms.cpms.entities.ServiceRequest;
import com.cpms.cpms.entities.ServiceRequest.RequestStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class ServiceRequestServiceTest {

    private ServiceRequestService serviceRequestService; // Service under test
    private ServiceRequestDAO serviceRequestDAOMock; // Mocked DAO dependency

    // Set up the mocked DAO and inject it into the ServiceRequestService
    @BeforeEach
    public void setUp() {
        serviceRequestDAOMock = mock(ServiceRequestDAO.class); // Mock the DAO
        serviceRequestService = new ServiceRequestService(); // Create service instance

        // Inject the mocked DAO into the ServiceRequestService instance using reflection
        try {
            java.lang.reflect.Field field = ServiceRequestService.class.getDeclaredField("requestDAO");
            field.setAccessible(true);
            field.set(serviceRequestService, serviceRequestDAOMock);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock DAO", e);
        }
    }

    // Test adding a service request
    @Test
    public void shouldAddServiceRequest() {
        ServiceRequest request = new ServiceRequest();
        request.setRequestDetails("Install security cameras");
        request.setRequestDate(Timestamp.valueOf("2025-04-20 14:00:00"));
        request.setStatus(RequestStatus.PENDING);

        serviceRequestService.addServiceRequest(request);

        verify(serviceRequestDAOMock, times(1)).addServiceRequest(request); // Verify DAO interaction
    }

    // Test retrieving a service request by ID
    @Test
    public void shouldGetServiceRequestById() {
        ServiceRequest mockRequest = new ServiceRequest();
        mockRequest.setRequestDetails("Install security cameras");
        mockRequest.setStatus(RequestStatus.IN_PROGRESS);

        when(serviceRequestDAOMock.getServiceRequest(1)).thenReturn(mockRequest); // Mock DAO behavior

        ServiceRequest result = serviceRequestService.getServiceRequestById(1);

        assertNotNull(result);
        assertEquals("Install security cameras", result.getRequestDetails());
        assertEquals(RequestStatus.IN_PROGRESS, result.getStatus());
        verify(serviceRequestDAOMock, times(1)).getServiceRequest(1); // Verify DAO interaction
    }

    // Test retrieving all service requests
    @Test
    public void shouldGetAllServiceRequests() {
        ServiceRequest request1 = new ServiceRequest();
        request1.setRequestDetails("Request1");
        ServiceRequest request2 = new ServiceRequest();
        request2.setRequestDetails("Request2");

        List<ServiceRequest> mockList = Arrays.asList(request1, request2);
        when(serviceRequestDAOMock.getAllServiceRequests()).thenReturn(mockList); // Mock DAO behavior

        List<ServiceRequest> result = serviceRequestService.getAllServiceRequests();

        assertEquals(2, result.size());
        assertEquals("Request1", result.get(0).getRequestDetails());
        assertEquals("Request2", result.get(1).getRequestDetails());
        verify(serviceRequestDAOMock, times(1)).getAllServiceRequests(); // Verify DAO interaction
    }

    // Test updating a service request
    @Test
    public void shouldUpdateServiceRequest() {
        ServiceRequest request = new ServiceRequest();
        request.setRequestID(1);
        request.setRequestDetails("Updated Details");

        serviceRequestService.updateServiceRequest(request);

        verify(serviceRequestDAOMock, times(1)).updateServiceRequest(request); // Verify DAO interaction
    }

    // Test deleting a service request by ID
    @Test
    public void shouldDeleteServiceRequestById() {
        serviceRequestService.deleteServiceRequest(5);

        verify(serviceRequestDAOMock, times(1)).deleteServiceRequest(5); // Verify DAO interaction
    }
}