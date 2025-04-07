package com.cpms.cpms.dao;

import com.cpms.cpms.entities.User;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAO {
	
	//user and password
	public User getUserByUsernameAndPassword(String username, String password) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        return session.createQuery("FROM User WHERE userName = :username AND password = :password", User.class)
	                      .setParameter("username", username)
	                      .setParameter("password", password)
	                      .uniqueResult();
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("Failed to fetch user with given username and password.");
	    }
	}

	public boolean addUser(User user) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        session.save(user);
	        transaction.commit();
	        return true;
	    } catch (Exception e) {
	        if (transaction != null) transaction.rollback();
	        e.printStackTrace();
	        return false;
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