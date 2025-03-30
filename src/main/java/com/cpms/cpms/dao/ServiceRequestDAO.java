package com.cpms.cpms.dao;

import com.cpms.cpms.entities.ServiceRequest;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ServiceRequestDAO {
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

    public ServiceRequest getServiceRequest(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(ServiceRequest.class, id);
        }
    }

    public List<ServiceRequest> getAllServiceRequests() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from ServiceRequest", ServiceRequest.class).list();
        }
    }

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