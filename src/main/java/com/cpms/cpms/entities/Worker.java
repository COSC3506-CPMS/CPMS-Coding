package com.cpms.cpms.entities;
import javax.persistence.*;

@Entity
@Table (name = "workers")
public class Worker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto-generated primary keys
	private int workerID;
	
	@Column(nullable = false, length = 100)//worker name
	private String workerName;
	
    @Column(nullable = false, length = 100) //contact info
    private String contactInfo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) 
    private Availability availability;

    @Column
    private String specialty; // Optional field for specialty

    @ManyToOne
    @JoinColumn(name = "assignedProjectID") // Worker assigned to a project (foreign key)
    private Project assignedProjectID;

    // Getters and setters
    //worker id
    public int getWorkerID() {
    	return workerID;
    }
    public void setWorkerID(int workerID) { 
    	this.workerID = workerID; 
    }
    
    //worker's name
    public String getWorkerName() {
    	return workerName; 
    }
    public void setWorkerName(String workerName) {
    	this.workerName = workerName;
    }
    
    //contact info
    public String getContactInfo() { 
    	return contactInfo;
    }
    public void setContactInfo(String contactInfo) { 
    	this.contactInfo = contactInfo;
    }
    
    //availability
    public Availability getAvailability() { 
    	return availability; 
    }
    public void setAvailability(Availability availability) { 
    	this.availability = availability;
    }
    
    //specialty
    public String getSpecialty() { 
    	return specialty;
    }
    public void setSpecialty(String specialty) {
    	this.specialty = specialty;
    }
    
    //projectId foreign key
    public Integer getAssignedProjectID() {
        return assignedProjectID != null ? assignedProjectID.getProjectID() : null;
    }
    public void setAssignedProjectID(Project assignedProjectID) {
    	this.assignedProjectID = assignedProjectID;
    }
  //enum for worker availability status
    public enum Availability {
        AVAILABLE, UNAVAILABLE
    }
}



	

