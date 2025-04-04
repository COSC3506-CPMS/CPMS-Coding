package com.cpms.cpms.dao;

import com.cpms.cpms.entities.User;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAO {

    // Add a new user to the database
    public void addUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user); // Save user object to the database
            transaction.commit(); // Commit the changes
            System.out.println("User added successfully!");
        } catch (Exception e) {
            System.err.println("Error adding user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Get a user by their ID
    public User getUser(int userID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, userID); // Fetch user by ID
            if (user == null) {
                System.out.println("User with ID " + userID + " not found.");
            }
            return user;
        } catch (Exception e) {
            System.err.println("Error fetching user with ID " + userID + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Update an existing user
    public void updateUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user); // Update user details
            transaction.commit(); // Commit changes
            System.out.println("User updated successfully!");
        } catch (Exception e) {
            System.err.println("Error updating user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Delete a user by ID
    public void deleteUser(int userID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, userID);
            if (user != null) {
                session.delete(user); // Delete user
                System.out.println("User with ID " + userID + " deleted successfully!");
            } else {
                System.out.println("No user found with ID " + userID);
            }
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Error deleting user with ID " + userID + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Retrieve all users
    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list(); // HQL query for all users
        } catch (Exception e) {
            System.err.println("Error retrieving all users: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}