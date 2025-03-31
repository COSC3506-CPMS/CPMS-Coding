package com.cpms.cpms.services;

import com.cpms.cpms.dao.UserDAO;
import com.cpms.cpms.entities.User;
import java.util.List;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    // Adds a new user if the user name is not null or empty
    public void addUser(User user) {
        if (user.getUserName() != null && !user.getUserName().isEmpty()) {
            userDAO.addUser(user);
        }
    }

    // Retrieves a user by their ID using UserDAO
    public User getUser(int userID) {
        return userDAO.getUser(userID);
    }

    // Updates an existing user using UserDAO
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    // Deletes a user by their ID using UserDAO
    public void deleteUser(int userID) {
        userDAO.deleteUser(userID);
    }

    // Retrieves all users using UserDAO
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}
