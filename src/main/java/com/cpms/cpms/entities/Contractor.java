package com.cpms.cpms.entities;

import javax.persistence.*;

@Entity
@Table(name = "contractors")
public class Contractor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Added auto-increment functionality
    private int contractorID; // Unique contractor ID

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false) // Ensures 'user' cannot be null
    private User user; // Associated user account

    @Column(nullable = true, length = 100) // Clarified optional field
    private String contractorName; // Contractor's full name

    @Column(nullable = true, length = 100) // Clarified optional field
    private String contactInfo; // Contact details (email/phone)

    // Getters and setters
    public int getContractorID() {
        return contractorID;
    }

    public void setContractorID(int contractorID) {
        this.contractorID = contractorID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}