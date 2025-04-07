package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Client;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ClientDAO {

	 public void addClient(Client client) {
	       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	           Transaction transaction = session.beginTransaction();
	           try {
	               session.save(client);
	               transaction.commit();
	           } catch (Exception e) {
	               transaction.rollback();
	               throw e; // Rethrow exception after rollback for proper handling
	           }
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	   }     
         
	
	 public Client getClient(int id) {
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            return session.get(Client.class, id); // Fetch the client by ID
	        } catch (Exception e) {
	            e.printStackTrace(); // Log exception for debugging
	            return null; // Ensure graceful handling of errors
	        }
	    }


    public List<Client> getAllClients() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Client", Client.class).list();
        }
    }

    public void updateClient(Client client) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    
    public void deleteClient(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Client client = session.get(Client.class, id);
            if (client != null) {
                session.delete(client);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}