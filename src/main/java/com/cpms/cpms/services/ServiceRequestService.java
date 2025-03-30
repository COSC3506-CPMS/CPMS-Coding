package com.cpms.cpms.services;

import com.cpms.cpms.dao.ServiceRequestDAO;
import com.cpms.cpms.entities.ServiceRequest;
import java.util.List;

public class ServiceRequestService {
    private ServiceRequestDAO requestDAO;

    public ServiceRequestService() {
        this.requestDAO = new ServiceRequestDAO();
    }

    // Adds a new service request to the database
    public void addServiceRequest(ServiceRequest request) {
        requestDAO.addServiceRequest(request);
    }

    // Retrieves a service request by its ID
    public ServiceRequest getServiceRequestById(int id) {
        return requestDAO.getServiceRequest(id);
    }

    // Retrieves all service requests from the database
    public List<ServiceRequest> getAllServiceRequests() {
        return requestDAO.getAllServiceRequests();
    }

    // Updates an existing service request
    public void updateServiceRequest(ServiceRequest request) {
        requestDAO.updateServiceRequest(request);
    }

    // Deletes a service request by its ID
    public void deleteServiceRequest(int id) {
        requestDAO.deleteServiceRequest(id);
    }
}