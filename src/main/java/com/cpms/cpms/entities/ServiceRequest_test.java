package com.cpms.cpms.entities;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;

public class ServiceRequestTest {

    @Test
    public void testSettersAndGetters() {
        ServiceRequest request = new ServiceRequest();

        int requestID = 101;
        String details = "Install security cameras";
        Timestamp requestDate = Timestamp.valueOf("2025-04-20 14:00:00");
        RequestStatus status = RequestStatus.IN_PROGRESS;

        // 더미 Client
        Client client = new Client();
        client.setClientID(201);
        client.setClientName("Tech Corp");
        client.setContactInfo("techcorp@example.com");

        // 더미 Project
        Project project = new Project();
        project.setProjectID(301);
        project.setProjectName("Office Renovation");

        // 필드 설정
        request.setRequestID(requestID);
        request.setRequestDetails(details);
        request.setRequestDate(requestDate);
        request.setStatus(status);
        request.setClient(client);
        request.setProject(project);

        // 검증
        Assert.assertEquals(requestID, request.getRequestID());
        Assert.assertEquals(details, request.getRequestDetails());
        Assert.assertEquals(requestDate, request.getRequestDate());
        Assert.assertEquals(status, request.getStatus());
        Assert.assertEquals(client, request.getClient());
        Assert.assertEquals(project, request.getProject());
    }
}
