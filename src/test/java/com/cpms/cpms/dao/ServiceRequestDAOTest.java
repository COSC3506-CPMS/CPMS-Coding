package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Client;
import com.cpms.cpms.entities.Project;
import com.cpms.cpms.entities.ServiceRequest;
import com.cpms.cpms.entities.ServiceRequest.RequestStatus;
import java.sql.Timestamp;

public class ServiceRequestDAOTest {
    public static void main(String[] args) {
        ServiceRequestDAO serviceRequestDAO = new ServiceRequestDAO();

        // Test adding a service request
        System.out.println("---- Testing Add Service Request ----");
        ServiceRequest request = new ServiceRequest();

        // Creating dummy client and project objects
        Client client = new Client();
        client.setClientID(2); // Example ClientID within the range 1-4
        client.setClientName("Tech Solutions");
        client.setClientContactInfo("contact@techsolutions.com");

        Project project = new Project();
        project.setProjectID(5); // Example ProjectID within the range 1-10
        project.setProjectName("Infrastructure Upgrade");

        // Setting required fields
        request.setClient(client);
        request.setProject(project);
        request.setRequestDetails("Set up new infrastructure for remote work.");
        request.setRequestDate(Timestamp.valueOf("2025-05-15 10:30:00"));
        request.setStatus(RequestStatus.IN_PROGRESS);

        serviceRequestDAO.addServiceRequest(request);
        System.out.println("Service request added: " + request);

        // Test getting a service request by ID
        System.out.println("---- Testing Get Service Request by ID ----");
        ServiceRequest retrievedRequest = serviceRequestDAO.getServiceRequest(request.getRequestID());
        if (retrievedRequest != null) {
            System.out.println("Retrieved Service Request: " + retrievedRequest);
        }

        // Test updating a service request
        System.out.println("---- Testing Update Service Request ----");
        if (retrievedRequest != null) {
            retrievedRequest.setRequestDetails("Updated infrastructure setup details.");
            serviceRequestDAO.updateServiceRequest(retrievedRequest);
            System.out.println("Service request updated: " + retrievedRequest);
        }

        // Test retrieving all service requests
        System.out.println("---- Testing Get All Service Requests ----");
        System.out.println("All Service Requests: " + serviceRequestDAO.getAllServiceRequests());

        // Test deleting a service request
       System.out.println("---- Testing Delete Service Request ----");
        if (retrievedRequest != null) {
            serviceRequestDAO.deleteServiceRequest(retrievedRequest.getRequestID());
            System.out.println("Service request deleted successfully!");
        }
    }
}