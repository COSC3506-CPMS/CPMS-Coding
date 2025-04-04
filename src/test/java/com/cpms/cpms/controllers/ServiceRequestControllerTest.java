package com.cpms.cpms.controllers;

import com.cpms.cpms.entities.ServiceRequest;
import com.cpms.cpms.entities.ServiceRequest.RequestStatus;
import com.cpms.cpms.services.ServiceRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ServiceRequestController.
 */
public class ServiceRequestControllerTest {

    private ServiceRequestController serviceRequestController; // Controller under test
    private ServiceRequestService serviceRequestServiceMock; // Mocked service dependency

    // Set up the mocked service and inject it into the ServiceRequestController
    @BeforeEach
    public void setUp() {
        serviceRequestServiceMock = mock(ServiceRequestService.class); // Mock the ServiceRequestService
        serviceRequestController = new ServiceRequestController();

        // Inject the mocked service into the ServiceRequestController instance using reflection
        try {
            java.lang.reflect.Field field = ServiceRequestController.class.getDeclaredField("requestService");
            field.setAccessible(true);
            field.set(serviceRequestController, serviceRequestServiceMock);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock service", e);
        }
    }

    // Test adding a service request
    @Test
    public void shouldAddServiceRequest() {
        ServiceRequest request = new ServiceRequest();
        request.setRequestDetails("Install security cameras");
        request.setRequestDate(Timestamp.valueOf("2025-04-20 14:00:00"));
        request.setStatus(RequestStatus.PENDING);

        serviceRequestController.addServiceRequest(request);

        verify(serviceRequestServiceMock, times(1)).addServiceRequest(request); // Verify service interaction
    }

    // Test retrieving a service request by ID
    @Test
    public void shouldGetServiceRequestById() {
        ServiceRequest mockRequest = new ServiceRequest();
        mockRequest.setRequestDetails("Install security cameras");
        mockRequest.setStatus(RequestStatus.IN_PROGRESS);

        when(serviceRequestServiceMock.getServiceRequestById(1)).thenReturn(mockRequest); // Mock service behavior

        ServiceRequest result = serviceRequestController.getServiceRequestById(1);

        assertNotNull(result);
        assertEquals("Install security cameras", result.getRequestDetails());
        assertEquals(RequestStatus.IN_PROGRESS, result.getStatus());
        verify(serviceRequestServiceMock, times(1)).getServiceRequestById(1); // Verify service interaction
    }

    // Test retrieving all service requests
    @Test
    public void shouldGetAllServiceRequests() {
        ServiceRequest request1 = new ServiceRequest();
        request1.setRequestDetails("Request1");
        ServiceRequest request2 = new ServiceRequest();
        request2.setRequestDetails("Request2");

        List<ServiceRequest> mockList = Arrays.asList(request1, request2);
        when(serviceRequestServiceMock.getAllServiceRequests()).thenReturn(mockList); // Mock service behavior

        List<ServiceRequest> result = serviceRequestController.getAllServiceRequests();

        assertEquals(2, result.size());
        assertEquals("Request1", result.get(0).getRequestDetails());
        assertEquals("Request2", result.get(1).getRequestDetails());
        verify(serviceRequestServiceMock, times(1)).getAllServiceRequests(); // Verify service interaction
    }

    // Test updating a service request
    @Test
    public void shouldUpdateServiceRequest() {
        ServiceRequest request = new ServiceRequest();
        request.setRequestID(1);
        request.setRequestDetails("Updated Details");

        serviceRequestController.updateServiceRequest(request);

        verify(serviceRequestServiceMock, times(1)).updateServiceRequest(request); // Verify service interaction
    }

    // Test deleting a service request by ID
    @Test
    public void shouldDeleteServiceRequestById() {
        serviceRequestController.deleteServiceRequest(5);

        verify(serviceRequestServiceMock, times(1)).deleteServiceRequest(5); // Verify service interaction
    }
}