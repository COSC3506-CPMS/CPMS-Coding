package com.cpms.cpms.dao;

import com.cpms.cpms.config.HibernateUtil;
import com.cpms.cpms.entities.ServiceRequest;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ServiceRequestDAOTest {

    private static ServiceRequestDAO requestDAO;

    @BeforeClass
    public static void setUp() {
        requestDAO = new ServiceRequestDAO();
    }

    @Test
    public void testAddAndGetServiceRequest() {
        ServiceRequest request = new ServiceRequest();
        request.setRequestID(1);
        request.setRequestTitle("Fix HVAC system");
        request.setRequestDescription("Air conditioning not working");
        request.setStatus("Pending");
        request.setRequestDate(Date.valueOf("2025-04-01"));

        requestDAO.addServiceRequest(request);

        ServiceRequest result = requestDAO.getServiceRequest(1);
        assertNotNull(result);
        assertEquals("Fix HVAC system", result.getRequestTitle());
    }

    @Test
    public void testUpdateServiceRequest() {
        ServiceRequest request = requestDAO.getServiceRequest(1);
        request.setStatus("Completed");
        requestDAO.updateServiceRequest(request);

        ServiceRequest updated = requestDAO.getServiceRequest(1);
        assertEquals("Completed", updated.getStatus());
    }

    @Test
    public void testGetAllServiceRequests() {
        List<ServiceRequest> list = requestDAO.getAllServiceRequests();
        assertTrue(list.size() >= 1);
    }

    @Test
    public void testDeleteServiceRequest() {
        requestDAO.deleteServiceRequest(1);
        ServiceRequest deleted = requestDAO.getServiceRequest(1);
        assertNull(deleted);
    }

    @AfterClass
    public static void tearDown() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        if (factory != null) factory.close();
    }
}
