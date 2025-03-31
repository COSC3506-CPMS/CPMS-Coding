package com.cpms.cpms.services;

import com.cpms.cpms.dao.ServiceRequestDAO;
import com.cpms.cpms.entities.ServiceRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServiceRequestServiceTest {

    private ServiceRequestService requestService;
    private ServiceRequestDAO requestDAOMock;

    @Before
    public void setUp() throws Exception {
        requestDAOMock = mock(ServiceRequestDAO.class);
        requestService = new ServiceRequestService();
      
        java.lang.reflect.Field field = ServiceRequestService.class.getDeclaredField("requestDAO");
        field.setAccessible(true);
        field.set(requestService, requestDAOMock);
    }

    @Test
    public void testAddServiceRequest() {
        ServiceRequest request = new ServiceRequest();
        requestService.addServiceRequest(request);
        verify(requestDAOMock).addServiceRequest(request);
    }

    @Test
    public void testGetServiceRequestById() {
        ServiceRequest mockRequest = new ServiceRequest();
        when(requestDAOMock.getServiceRequest(1)).thenReturn(mockRequest);

        ServiceRequest result = requestService.getServiceRequestById(1);
        assertEquals(mockRequest, result);
    }

    @Test
    public void testGetAllServiceRequests() {
        List<ServiceRequest> mockList = Arrays.asList(new ServiceRequest(), new ServiceRequest());
        when(requestDAOMock.getAllServiceRequests()).thenReturn(mockList);

        List<ServiceRequest> result = requestService.getAllServiceRequests();
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateServiceRequest() {
        ServiceRequest request = new ServiceRequest();
        requestService.updateServiceRequest(request);
        verify(requestDAOMock).updateServiceRequest(request);
    }

    @Test
    public void testDeleteServiceRequest() {
        requestService.deleteServiceRequest(77);
        verify(requestDAOMock).deleteServiceRequest(77);
    }
}
