package com.cpms.cpms.dao;

import com.cpms.cpms.entities.FinancialTransaction;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class FinancialTransactionDAO {
    public void addTransaction(FinancialTransaction transaction) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(transaction);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public FinancialTransaction getTransaction(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(FinancialTransaction.class, id);
        }
    }

    public List<FinancialTransaction> getAllTransactions() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from FinancialTransaction", FinancialTransaction.class).list();
        }
    }

    public void updateTransaction(FinancialTransaction transaction) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(transaction);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void deleteTransaction(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            FinancialTransaction transaction = session.get(FinancialTransaction.class, id);
            if (transaction != null) {
                session.delete(transaction);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}