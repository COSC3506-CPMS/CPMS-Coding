package com.cpms.cpms.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
public class User {

    @Id
    private int userID; // Unique user ID

    @Column(nullable = false, length = 50)
    private String userName; // User's name (max 50 chars)

    @Column(nullable = false)
    private String password; // Hashed password

    @Column(nullable = false)
    private String role; // User role: Admin, Contractor, Client

    @Column
    private String permissions; // Additional permissions

    // Getters and setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
