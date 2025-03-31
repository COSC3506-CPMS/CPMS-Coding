package com.cpms.cpms.controllers;


import com.cpms.cpms.services.UserService;
import com.cpms.cpms.entities.User;
import java.util.List;

public class UserController {
    private UserService userService = new UserService(); // Inject UserService

    public void addUser(String name, String password, String role, String permissions) {
        User user = new User();
        user.setUserName(name);
        user.setPassword(password);
        user.setRole(role);
        user.setPermissions(permissions);
        userService.addUser(user); // Save user
    }

    public User getUserById(int id) {
        return userService.getUser(id); // Retrieve user by ID
    }

    public void updateUser(User user) {
        userService.updateUser(user); // Update user
    }

    public void deleteUser(int id) {
        userService.deleteUser(id); // Delete user by ID
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers(); // Retrieve all users
    }
}

