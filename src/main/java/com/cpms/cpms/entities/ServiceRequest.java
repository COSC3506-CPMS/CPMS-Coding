package com.cpms.cpms.entities;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class ServiceRequest {
	 @Id
	 private int requestID; //request id as a primary key

	 @ManyToOne
	 @JoinColumn(name = "clientID", nullable = false) //client id as a foreign key 
	 private Client client;

	 @ManyToOne
	 @JoinColumn(name = "projectID", nullable = false) //project id as a foreign key
	 private Project project;
	 
	 @Column(nullable = false) //Details about the service request
	 private String requestDetails;

	 @Column(nullable = false) //Date of service request
	 private Timestamp requestDate;

	 @Enumerated(EnumType.STRING) //Enum for request status 
	 @Column(nullable = false)
	 private RequestStatus status;

	// Getters and setters
	//request id 
	public int getRequestID() {
	    return requestID;
	}
    public void setRequestID(int requestID) {
       this.requestID = requestID;
	}
    
    //client id
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    
    //project id
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    
    //request details
    public String getRequestDetails() {
        return requestDetails;
    }
    public void setRequestDetails(String requestDetails) {
        this.requestDetails = requestDetails;
    }
    
    //request date
    public Timestamp getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }
    
    //request status
    public RequestStatus getStatus() {
        return status;
    }
    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
//enum for service request
enum RequestStatus {
    PENDING, IN_PROGRESS, RESOLVED 
}


