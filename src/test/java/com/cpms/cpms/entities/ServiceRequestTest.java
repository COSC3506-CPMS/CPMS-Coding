package com.cpms.cpms.entities;

import org.junit.jupiter.api.Test;
import java.sql.Timestamp;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceRequestTest {

    @Test
    public void testSettersAndGetters() {
        ServiceRequest request = new ServiceRequest();

        int requestID = 101;
        String details = "Install security cameras.";
        Timestamp requestDate = Timestamp.valueOf("2025-05-01 12:00:00");
        int serviceClientID = 42;

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