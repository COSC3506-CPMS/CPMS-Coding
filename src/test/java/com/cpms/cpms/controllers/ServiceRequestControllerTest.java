package com.cpms.cpms.controllers;

import com.cpms.cpms.entities.ServiceRequest;
import com.cpms.cpms.services.ServiceRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceRequestControllerTest {

    private ServiceRequestController serviceRequestController;
    private ServiceRequestService serviceRequestServiceMock;

    @BeforeEach
    public void setUp() {
        serviceRequestServiceMock = mock(ServiceRequestService.class);
        serviceRequestController = new ServiceRequestController();

        try {
            java.lang.reflect.Field field = ServiceRequestController.class.getDeclaredField("requestService");
            field.setAccessible(true);
            field.set(serviceRequestController, serviceRequestServiceMock);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock service", e);
        }
    }

    @Test
    public void shouldAddServiceRequest() {
        ServiceRequest request = new ServiceRequest();
        request.setRequestDetails("Install security cameras");
        request.setRequestDate(Timestamp.valueOf("2025-04-20 14:00:00"));
       

        serviceRequestController.addServiceRequest(request);

        verify(serviceRequestServiceMock, times(1)).addServiceRequest(request);
    }

    @Test
    public void shouldGetServiceRequestById() {
        ServiceRequest mockRequest = new ServiceRequest();
        mockRequest.setRequestDetails("Install security cameras");
       

        when(serviceRequestServiceMock.getServiceRequestById(1)).thenReturn(mockRequest);

        ServiceRequest result = serviceRequestController.getServiceRequestById(1);

        assertNotNull(result);
        assertEquals("Install security cameras", result.getRequestDetails());
 
        verify(serviceRequestServiceMock, times(1)).getServiceRequestById(1);
    }

    @Test
    public void shouldGetAllServiceRequests() {
        ServiceRequest request1 = new ServiceRequest();
        request1.setRequestDetails("Request1");
        ServiceRequest request2 = new ServiceRequest();
        request2.setRequestDetails("Request2");

        List<ServiceRequest> mockList = Arrays.asList(request1, request2);
        when(serviceRequestServiceMock.getAllServiceRequests()).thenReturn(mockList);

        List<ServiceRequest> result = serviceRequestController.getAllServiceRequests();

        assertEquals(2, result.size());
        assertEquals("Request1", result.get(0).getRequestDetails());
        assertEquals("Request2", result.get(1).getRequestDetails());
        verify(serviceRequestServiceMock, times(1)).getAllServiceRequests();
    }

    @Test
    public void shouldUpdateServiceRequest() {
        ServiceRequest request = new ServiceRequest();
        request.setRequestID(1);
        request.setRequestDetails("Updated Details");

        serviceRequestController.updateServiceRequest(request);

        verify(serviceRequestServiceMock, times(1)).updateServiceRequest(request);
    }

    @Test
    public void shouldDeleteServiceRequestById() {
        serviceRequestController.deleteServiceRequest(5);

        verify(serviceRequestServiceMock, times(1)).deleteServiceRequest(5);
    }
}