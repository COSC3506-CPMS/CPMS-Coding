package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Invoice;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class InvoiceDAO {
    public void addInvoice(Invoice invoice) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(invoice);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Invoice getInvoice(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Invoice.class, id);
        }
    }

    public List<Invoice> getAllInvoices() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Invoice", Invoice.class).list();
        }
    }

    public void updateInvoice(Invoice invoice) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(invoice);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void deleteInvoice(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Invoice invoice = session.get(Invoice.class, id);
            if (invoice != null) {
                session.delete(invoice);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}