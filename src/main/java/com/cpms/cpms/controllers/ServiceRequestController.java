package com.cpms.cpms.controllers;

import com.cpms.cpms.entities.ServiceRequest;
import com.cpms.cpms.services.ServiceRequestService;
import java.util.List;

public class ServiceRequestController {
    private ServiceRequestService requestService;

    public ServiceRequestController() {
        this.requestService = new ServiceRequestService();
    }

    //Adds a new service request
    public void addServiceRequest(ServiceRequest request) {
        requestService.addServiceRequest(request);
    }

    //Retrieves a service request by its ID
    public ServiceRequest getServiceRequestById(int id) {
        return requestService.getServiceRequestById(id);
    }

    //Retrieves all service requests
    public List<ServiceRequest> getAllServiceRequests() {
        return requestService.getAllServiceRequests();
    }

    //Updates an existing service request
    public void updateServiceRequest(ServiceRequest request) {
        requestService.updateServiceRequest(request);
    }

    //Deletes a service request by its ID
    public void deleteServiceRequest(int id) {
        requestService.deleteServiceRequest(id);
    }
}