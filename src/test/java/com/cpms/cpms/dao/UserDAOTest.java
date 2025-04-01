package com.cpms.cpms.dao;

import com.cpms.cpms.entities.User;

public class UserDAOTest {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();

        // Test adding a user
        System.out.println("---- Testing Add User ----");
        User user = new User();
        user.setUserName("Jim Wang");
        user.setPassword("secure123");
        user.setRole(User.Role.Admin); // Enum for role
        user.setPermissions("READ,WRITE");

        userDAO.addUser(user); // Add user
        System.out.println("User added: " + user);

        // Test getting a user by ID
        System.out.println("---- Testing Get User by ID ----");
        User retrievedUser = userDAO.getUser(user.getUserID());
        if (retrievedUser != null) {
            System.out.println("Retrieved User: " + retrievedUser);
        }

        // Test updating a user
        System.out.println("---- Testing Update User ----");
        if (retrievedUser != null) {
            retrievedUser.setPassword("NewPass321");
            userDAO.updateUser(retrievedUser);
            System.out.println("User updated: " + retrievedUser);
        }

        // Test retrieving all users
        System.out.println("---- Testing Get All Users ----");
        System.out.println("All Users: " + userDAO.getAllUsers());

        // Test deleting a user
        System.out.println("---- Testing Delete User ----");
        if (retrievedUser != null) {
            userDAO.deleteUser(retrievedUser.getUserID());
            System.out.println("User deleted successfully!");
        }
    }
}