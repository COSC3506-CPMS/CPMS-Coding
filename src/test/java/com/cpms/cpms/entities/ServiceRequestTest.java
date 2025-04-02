package com.cpms.cpms.entities;

import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceRequestTest {

    // Test setters and getters for ServiceRequest
    @Test
    public void shouldTestSettersAndGetters() {
        ServiceRequest request = new ServiceRequest();

        int requestID = 101;
        String details = "Install security cameras";
        Timestamp requestDate = Timestamp.valueOf("2025-04-20 14:00:00");
        ServiceRequest.RequestStatus status = ServiceRequest.RequestStatus.IN_PROGRESS; // Fully qualified enum

        // Mock Client
        Client client = new Client();
        client.setClientID(201);
        client.setClientName("Tech Corp");
        client.setContactInfo("techcorp@example.com");

        // Mock Project
        Project project = new Project();
        project.setProjectID(301);
        project.setProjectName("Office Renovation");

        // Set fields
        request.setRequestID(requestID);
        request.setRequestDetails(details);
        request.setRequestDate(requestDate);
        request.setStatus(status);
        request.setClient(client);
        request.setProject(project);

        // Verify fields
        assertEquals(requestID, request.getRequestID());
        assertEquals(details, request.getRequestDetails());
        assertEquals(requestDate, request.getRequestDate());
        assertEquals(status, request.getStatus());
        assertEquals(client, request.getClient());
        assertEquals(project, request.getProject());
    }
}