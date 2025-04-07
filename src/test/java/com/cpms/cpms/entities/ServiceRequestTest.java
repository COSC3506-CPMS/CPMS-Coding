package com.cpms.cpms.entities;

import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceRequestTest {

    @Test
    public void testSettersAndGetters() {
        ServiceRequest request = new ServiceRequest();

        int requestID = 101;
<<<<<<< HEAD
        String details = "Install security cameras.";
        Timestamp requestDate = Timestamp.valueOf("2025-05-01 12:00:00");
        int serviceClientID = 42;
=======
        String details = "Install security cameras";
        Timestamp requestDate = Timestamp.valueOf("2025-04-20 14:00:00");
        ServiceRequest.RequestStatus status = ServiceRequest.RequestStatus.IN_PROGRESS; // Fully qualified enum

        // Mock Client
        Client client = new Client();
        client.setClientID(201);
        client.setClientName("Tech Corp");
        client.setClientContactInfo("techcorp@example.com");

        // Mock Project
        Project project = new Project();
        project.setProjectID(301);
        project.setProjectName("Office Renovation");
>>>>>>> komal-finalWork

        // Set fields
        request.setRequestID(requestID);
        request.setRequestDetails(details);
        request.setRequestDate(requestDate);
        request.setServiceClientID(serviceClientID);

        // Verify fields
        assertEquals(requestID, request.getRequestID());
        assertEquals(details, request.getRequestDetails());
        assertEquals(requestDate, request.getRequestDate());
        assertEquals(serviceClientID, request.getServiceClientID());
    }
}