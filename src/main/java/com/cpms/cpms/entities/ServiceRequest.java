package com.cpms.cpms.entities;

import javax.persistence.*;
import java.sql.Timestamp;

// Represents a service request entity
@Entity
@Table(name = "service_requests")
public class ServiceRequest {

    // Request ID as the primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestID;

    // Client ID as a foreign key
    @ManyToOne
    @JoinColumn(name = "clientID", nullable = false)
    private Client client;

    // Project ID as a foreign key
    @ManyToOne
    @JoinColumn(name = "projectID", nullable = false)
    private Project project;

    // Details about the service request
    @Column(nullable = false)
    private String requestDetails;

    // Date of the service request
    @Column(nullable = false)
    private Timestamp requestDate;

    // Request status
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;

    // Getter and Setter for request ID
    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    // Getter and Setter for client ID
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    // Getter and Setter for project ID
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    // Getter and Setter for request details
    public String getRequestDetails() {
        return requestDetails;
    }

    public void setRequestDetails(String requestDetails) {
        this.requestDetails = requestDetails;
    }

    // Getter and Setter for request date
    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    // Getter and Setter for request status
    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
    // Enum for service request status
    public enum RequestStatus {
        PENDING, IN_PROGRESS, RESOLVED
    }
}

