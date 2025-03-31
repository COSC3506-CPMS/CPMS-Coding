package com.cpms.cpms.dao;

import com.cpms.cpms.entities.User;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDAO {
	
	//add new user to database
	public void addUser(User user) {
		
		//open hibernate
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		//Begin transaction
		Transaction transaction = session.beginTransaction();
		
		//save user object to database
		session.save(user);
		
		//save changes permanently
		transaction.commit();
		
		//close sessions to free resources
		session.close();		
	}
	
	//method to get user by its id
	public User getUser(int userID) {
		
		//open hibernate session
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		//get user using its id
		User user = session.get(User.class, userID);
		
		session.close();
		
		return user;
	}
	
	//update user
	public void updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction transaction = session.beginTransaction();
        
        session.update(user);
        
        transaction.commit();
        
        session.close();
	    }


	//method to delete a user
	public void deleteUser(int userID) {
		
		//open hibernate session
		Session session = HibernateUtil.getSessionFactory().openSession();
	
		//begin transaction
		Transaction transaction = session.beginTransaction();
		
		//get user by id
		User user = session.get(User.class, userID);

		//if user exists, delete it
		if(user!= null) {
			session.delete(user);
		}
		
		//save changes
		transaction.commit();
		
		//close session
		session.close();
	}
	
    // Method to retrieve all Users from the database
    public List<User> getAllUsers() {
        
    	// Open a Hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        // Create an HQL (Hibernate Query Language) query to get all Users
        List<User> users = session.createQuery("from User", User.class).list();
        
        // Close the session
        session.close();
        
        // Return the list of users
        return users;
    }
}
