package com.cpms.cpms.entities;
import javax.persistence.*;
@Entity
@Table(name = "users") // Match the exact table name in your database
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int userID; // Unique user ID
   @Column(nullable = false, length = 50)
   private String userName; // User's name (max 50 chars)
   @Column(nullable = false, length = 255)
   private String password; // Hashed password (max 255 chars)
   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private Role role; // User role: Admin, Contractor, Client
   @Column(columnDefinition = "TEXT")
   private String permissions; // Additional permissions
   // Enum for Role
   public enum Role {
       ADMIN, CONTRACTOR, CLIENT
   }
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
   public Role getRole() {
       return role;
   }
   public void setRole(Role role) {
       this.role = role;
   }
   public String getPermissions() {
       return permissions;
   }
   public void setPermissions(String permissions) {
       this.permissions = permissions;
   }
   // toString method for better readability in output
   @Override
   public String toString() {
       return "User{" +
               "userID=" + userID +
               ", userName='" + userName + '\'' +
               ", role=" + role +
               ", permissions='" + permissions + '\'' +
               '}';
   }
}
