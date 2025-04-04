package com.cpms.cpms.dao;

import com.cpms.cpms.entities.FinancialTransaction;
import com.cpms.cpms.config.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FinancialTransactionDAO {

    // Add a new financial transaction to the database
    public void addTransaction(FinancialTransaction transaction) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(transaction); // Save transaction object to the database
            tx.commit(); // Commit the changes
            System.out.println("Transaction added successfully!");
        } catch (Exception e) {
            System.err.println("Error adding transaction: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Get a financial transaction by its ID
    public FinancialTransaction getTransaction(int transactionID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            FinancialTransaction transaction = session.get(FinancialTransaction.class, transactionID); // Fetch transaction by ID
            if (transaction == null) {
                System.out.println("Transaction with ID " + transactionID + " not found.");
            }
            return transaction;
        } catch (Exception e) {
            System.err.println("Error fetching transaction with ID " + transactionID + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Update an existing financial transaction
    public void updateTransaction(FinancialTransaction transaction) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(transaction); // Update transaction details
            tx.commit(); // Commit changes
            System.out.println("Transaction updated successfully!");
        } catch (Exception e) {
            System.err.println("Error updating transaction: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Delete a financial transaction by its ID
    public void deleteTransaction(int transactionID) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            FinancialTransaction transaction = session.get(FinancialTransaction.class, transactionID);
            if (transaction != null) {
                session.delete(transaction); // Delete transaction
                System.out.println("Transaction with ID " + transactionID + " deleted successfully!");
            } else {
                System.out.println("No transaction found with ID " + transactionID);
            }
            tx.commit();
        } catch (Exception e) {
            System.err.println("Error deleting transaction with ID " + transactionID + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Retrieve all financial transactions
    public List<FinancialTransaction> getAllTransactions() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from FinancialTransaction", FinancialTransaction.class).list(); // HQL query for all transactions
        } catch (Exception e) {
            System.err.println("Error retrieving all transactions: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
