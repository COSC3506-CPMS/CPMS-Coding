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
    @JoinColumn(name = "clientUserID", nullable = false)
    private User clientUserID; // Foreign key linking to user table


    @JoinColumn(name = "clientName")
    @Column(nullable = false, length = 100)
    private String clientName; // Client's name


    @JoinColumn(name = "clientContactInfo")
    @Column(nullable = false, length = 100)
    private String clientContactInfo; // Client's contact information


    // Getters and Setters
    public int getClientID() {
        return clientID;
    }


    public void setClientID(int clientID) {
        this.clientID = clientID;
    }


    public User getClientUserID() {
        return clientUserID;
    }


    public void setClientUserID(User clientUserID) {
        this.clientUserID = clientUserID;
    }


    public String getClientName() {
        return clientName;
    }


    public void setClientName(String clientName) {
        this.clientName = clientName;
    }


    public String getClientContactInfo() {
        return clientContactInfo;
    }


    public void setClientContactInfo(String clientContactInfo) {
        this.clientContactInfo = clientContactInfo;
    }


}
