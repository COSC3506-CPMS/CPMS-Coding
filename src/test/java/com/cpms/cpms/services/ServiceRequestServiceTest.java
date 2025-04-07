package com.cpms.cpms.services;

import com.cpms.cpms.dao.ServiceRequestDAO;
import com.cpms.cpms.entities.ServiceRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceRequestServiceTest {

    private ServiceRequestService serviceRequestService;
    private ServiceRequestDAO serviceRequestDAOMock;

    @BeforeEach
    public void setUp() {
        serviceRequestDAOMock = mock(ServiceRequestDAO.class);
        serviceRequestService = new ServiceRequestService();

        try {
            java.lang.reflect.Field field = ServiceRequestService.class.getDeclaredField("requestDAO");
            field.setAccessible(true);
            field.set(serviceRequestService, serviceRequestDAOMock);
        } catch (Exception e) {
            throw new RuntimeException("Failed to inject mock DAO", e);
        }
    }

    @Test
    public void shouldAddServiceRequest() {
        ServiceRequest request = new ServiceRequest();
        request.setRequestDetails("Install security cameras");
        request.setRequestDate(Timestamp.valueOf("2025-04-20 14:00:00"));
        request.setServiceClientID(1);

        serviceRequestService.addServiceRequest(request);

        verify(serviceRequestDAOMock, times(1)).addServiceRequest(request);
    }

    @Test
    public void shouldGetServiceRequestById() {
        ServiceRequest mockRequest = new ServiceRequest();
        mockRequest.setRequestDetails("Install security cameras");
        mockRequest.setServiceClientID(1);

        when(serviceRequestDAOMock.getServiceRequest(1)).thenReturn(mockRequest);

        ServiceRequest result = serviceRequestService.getServiceRequestById(1);

        assertNotNull(result);
        assertEquals("Install security cameras", result.getRequestDetails());
        assertEquals(1, result.getServiceClientID());
        verify(serviceRequestDAOMock, times(1)).getServiceRequest(1);
    }

    @Test
    public void shouldGetAllServiceRequests() {
        ServiceRequest request1 = new ServiceRequest();
        request1.setRequestDetails("Request1");
        request1.setServiceClientID(1);

        ServiceRequest request2 = new ServiceRequest();
        request2.setRequestDetails("Request2");
        request2.setServiceClientID(2);

        List<ServiceRequest> mockList = Arrays.asList(request1, request2);
        when(serviceRequestDAOMock.getAllServiceRequests()).thenReturn(mockList);

        List<ServiceRequest> result = serviceRequestService.getAllServiceRequests();

        assertEquals(2, result.size());
        assertEquals("Request1", result.get(0).getRequestDetails());
        assertEquals("Request2", result.get(1).getRequestDetails());
        verify(serviceRequestDAOMock, times(1)).getAllServiceRequests();
    }

    @Test
    public void shouldUpdateServiceRequest() {
        ServiceRequest request = new ServiceRequest();
        request.setRequestID(1);
        request.setRequestDetails("Updated Details");
        request.setServiceClientID(1);

        serviceRequestService.updateServiceRequest(request);

        verify(serviceRequestDAOMock, times(1)).updateServiceRequest(request);
    }

    @Test
    public void shouldDeleteServiceRequestById() {
        serviceRequestService.deleteServiceRequest(5);

        verify(serviceRequestDAOMock, times(1)).deleteServiceRequest(5);
    }
}