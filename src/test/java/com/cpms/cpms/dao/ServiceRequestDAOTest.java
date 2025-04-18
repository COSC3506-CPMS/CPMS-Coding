package com.cpms.cpms.dao;

import com.cpms.cpms.entities.ServiceRequest;
import org.junit.jupiter.api.*;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceRequestDAOTest {

    private static ServiceRequestDAO serviceRequestDAO;

    @BeforeAll
    public static void setUp() {
        serviceRequestDAO = new ServiceRequestDAO(); // Initialize DAO
    }

    @Test
    public void testAddServiceRequest() {
        // Arrange
        ServiceRequest request = new ServiceRequest();
        request.setRequestDetails("Fix water leakage in building.");
        request.setRequestDate(new Timestamp(System.currentTimeMillis()));
        request.setServiceClientID(1); // Assuming valid client ID exists

        // Act
        serviceRequestDAO.addServiceRequest(request);

        // Assert
        assertTrue(request.getRequestID() > 0, "Request ID should be auto-generated");
    }

    @Test
    public void testGetServiceRequest() {
        // Arrange
        ServiceRequest request = new ServiceRequest();
        request.setRequestDetails("Install security cameras.");
        request.setRequestDate(new Timestamp(System.currentTimeMillis()));
        request.setServiceClientID(2); // Assuming valid client ID exists
        serviceRequestDAO.addServiceRequest(request);

        // Act
        ServiceRequest retrievedRequest = serviceRequestDAO.getServiceRequest(request.getRequestID());

        // Assert
        assertNotNull(retrievedRequest, "Retrieved request should not be null");
        assertEquals("Install security cameras.", retrievedRequest.getRequestDetails());
    }

    @Test
    public void testGetAllServiceRequests() {
        // Act
        List<ServiceRequest> requests = serviceRequestDAO.getAllServiceRequests();

        // Assert
        assertNotNull(requests, "Request list should not be null");
        assertTrue(requests.size() > 0, "There should be at least one request in the list");
    }

    @Test
    public void testUpdateServiceRequest() {
        // Arrange
        ServiceRequest request = new ServiceRequest();
        request.setRequestDetails("Initial request details.");
        request.setRequestDate(new Timestamp(System.currentTimeMillis()));
        request.setServiceClientID(3);
        serviceRequestDAO.addServiceRequest(request);

        // Act
        request.setRequestDetails("Updated request details.");
        serviceRequestDAO.updateServiceRequest(request);
        ServiceRequest updatedRequest = serviceRequestDAO.getServiceRequest(request.getRequestID());

        // Assert
        assertEquals("Updated request details.", updatedRequest.getRequestDetails());
    }

    @Test
    public void testDeleteServiceRequest() {
        // Arrange
        ServiceRequest request = new ServiceRequest();
        request.setRequestDetails("Request to be deleted.");
        request.setRequestDate(new Timestamp(System.currentTimeMillis()));
        request.setServiceClientID(4);
        serviceRequestDAO.addServiceRequest(request);
        int requestId = request.getRequestID();

        // Act
        serviceRequestDAO.deleteServiceRequest(requestId);
        ServiceRequest deletedRequest = serviceRequestDAO.getServiceRequest(requestId);

        // Assert
        assertNull(deletedRequest, "Deleted request should not exist in the database");
    }
}