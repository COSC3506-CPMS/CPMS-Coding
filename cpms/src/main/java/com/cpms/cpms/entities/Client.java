package com.cpms.cpms.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class Client {

	@Id
	private int clientID;      //primary key for client table;
	
	@ManyToOne
	@JoinColumn(name = "userID", nullable = false)
	private User user;    //Foreign key linking to user table
	
	@Column(nullable = false, length = 100)
	private String clientName;  //client's Name
	
	@Column(nullable = false, length = 100)
	private String contactInfo;  //client's contact information
	
	//getters and setters
	//client ID
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	
	//user 
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	//client name
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	//client's contact info
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	
}
