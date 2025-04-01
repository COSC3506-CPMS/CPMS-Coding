package com.cpms.cpms.controllers;

import com.cpms.cpms.services.UserService;
import com.cpms.cpms.entities.User;
import java.util.List;

public class UserController {
    private final UserService userService; // Use constructor injection for UserService

    // Constructor-based Dependency Injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void addUser(String name, String password, User.Role role, String permissions) {
        User user = new User();
        user.setUserName(name);
        user.setPassword(password);
        user.setRole(role);
        user.setPermissions(permissions);
        userService.addUser(user);
    }

    public User getUserById(int id) {
        return userService.getUser(id);
    }

    public void updateUser(User user) {
        userService.updateUser(user);
    }

    public void deleteUser(int id) {
        userService.deleteUser(id);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}