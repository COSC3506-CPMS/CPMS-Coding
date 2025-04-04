package com.cpms.cpms.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "clients") // Specifies the table name in the database
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientID;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user; // Foreign key linking to user table

    @Column(nullable = false, length = 100)
    private String clientName; // Client's name

    @Column(nullable = false, length = 100)
    private String contactInfo; // Client's contact information

    // Getters and Setters
    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientID=" + clientID +
                ", user=" + (user != null ? user.getUserID() : "null") +
                ", clientName='" + clientName + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}