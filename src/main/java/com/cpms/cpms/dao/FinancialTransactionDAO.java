package com.cpms.cpms.dao;

import com.cpms.cpms.entities.FinancialTransaction;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FinancialTransactionDAO {

    // Adds a new financial transaction to the database
    public void addTransaction(FinancialTransaction transaction) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(transaction); // Save the transaction to the database
            tx.commit(); // Commit the transaction
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); // Roll back the transaction in case of failure
            }
            e.printStackTrace(); // Log the exception
        }
    }

    // Retrieves a financial transaction by its ID
    public FinancialTransaction getTransaction(int transactionID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(FinancialTransaction.class, transactionID); // Retrieve transaction using ID
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return null;
        }
    }

    // Updates an existing financial transaction in the database
    public void updateTransaction(FinancialTransaction transaction) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(transaction); // Update the transaction in the database
            tx.commit(); // Commit the transaction
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); // Roll back the transaction in case of failure
            }
            e.printStackTrace(); // Log the exception
        }
    }

    // Deletes a financial transaction by its ID if it exists
    public void deleteTransaction(int transactionID) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            FinancialTransaction transaction = session.get(FinancialTransaction.class, transactionID); // Retrieve the transaction
            if (transaction != null) {
                session.delete(transaction); // Delete the transaction
                tx.commit(); // Commit the transaction
            } else {
                if (tx != null) {
                    tx.rollback(); // Roll back if transaction not found
                }
                System.out.println("Transaction with ID " + transactionID + " not found."); // Log information
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); // Roll back the transaction in case of failure
            }
            e.printStackTrace(); // Log the exception
        }
    }

    // Retrieves a list of all financial transactions from the database
    public List<FinancialTransaction> getAllTransactions() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM FinancialTransaction", FinancialTransaction.class).list(); // HQL query to get all transactions
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return null;
        }
    }
}