package com.cpms.cpms.dao;

import com.cpms.cpms.entities.ServiceRequest;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

// DAO class for handling database operations for ServiceRequest
public class ServiceRequestDAO {

    // Adds a new service request to the database
    public void addServiceRequest(ServiceRequest request) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(request);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Retrieves a service request by its ID
    public ServiceRequest getServiceRequest(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(ServiceRequest.class, id);
        }
    }

    // Retrieves all service requests from the database
    public List<ServiceRequest> getAllServiceRequests() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from ServiceRequest", ServiceRequest.class).list();
        }
    }

    // Updates an existing service request in the database
    public void updateServiceRequest(ServiceRequest request) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(request);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Deletes a service request by its ID
    public void deleteServiceRequest(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            ServiceRequest request = session.get(ServiceRequest.class, id);
            if (request != null) {
                session.delete(request);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}