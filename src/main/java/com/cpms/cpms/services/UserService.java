package com.cpms.cpms.services;

import com.cpms.cpms.dao.UserDAO;
import com.cpms.cpms.entities.User;
import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    // Constructor-based Dependency Injection
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addUser(User user) {
        if (user.getUserName() == null || user.getUserName().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty.");
        }
        userDAO.addUser(user);
    }

    public User getUser(int userID) {
        return userDAO.getUser(userID);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(int userID) {
        userDAO.deleteUser(userID);
    }
    

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}