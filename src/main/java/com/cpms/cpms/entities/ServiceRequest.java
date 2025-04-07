package com.cpms.cpms.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "service_requests")
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestID;

    @Column(nullable = false)
    private String requestDetails;

    @Column(nullable = false)
    private Timestamp requestDate;

    @Column(nullable = false)
    private int serviceClientID; // Refers to the client ID (ServiceClientID)

    // Getters and Setters
    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getRequestDetails() {
        return requestDetails;
    }

    public void setRequestDetails(String requestDetails) {
        this.requestDetails = requestDetails;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public int getServiceClientID() {
        return serviceClientID;
    }

    public void setServiceClientID(int serviceClientID) {
        this.serviceClientID = serviceClientID;
    }
}