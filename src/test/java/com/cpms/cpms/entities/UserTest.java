package com.cpms.cpms.entities;

import org.junit.jupiter.api.Test; // Using JUnit 5 for testing
import org.junit.jupiter.api.Assertions; // Updated import for assertions

public class UserTest {

    @Test
    public void testUserSettersAndGetters() {
        // Create a new User object
        User user = new User();

        // Values to test
        int userID = 100; 
        String userName = "jane_doe";
        String password = "secure_pw_123";

        // Use the Enum for Role
        User.Role role = User.Role.CLIENT;

        // Set values using setter methods
        user.setUserID(userID);
        user.setUserName(userName);
        user.setPassword(password);
        user.setRole(role);

        // Validate values using getter methods and assertions
        Assertions.assertEquals(userID, user.getUserID());
        Assertions.assertEquals(userName, user.getUserName());
        Assertions.assertEquals(password, user.getPassword());
        Assertions.assertEquals(role, user.getRole()); // Assert using Enum value
    }
}