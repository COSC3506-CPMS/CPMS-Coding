package com.cpms.cpms.dao;

import com.cpms.cpms.entities.FinancialTransaction;
import com.cpms.cpms.entities.Project;
import java.sql.Timestamp;

public class FinancialTransactionDAOTest {
    public static void main(String[] args) {
        FinancialTransactionDAO financialTransactionDAO = new FinancialTransactionDAO();

        // Test adding a transaction
        System.out.println("---- Testing Add Transaction ----");
        FinancialTransaction transaction = new FinancialTransaction();
        
        // Create and set a valid Project
        Project project = new Project();
        project.setProjectID(1);  // Set a valid project ID
        
        // Now assign the project to the transaction
        transaction.setProject(project);
        
        // Set other transaction details
        transaction.setTransactionID(1);
        transaction.setAmount(5000.75);
        transaction.setDate(new Timestamp(System.currentTimeMillis()));
        transaction.setType(FinancialTransaction.TransactionType.PAYMENT); // Enum for type

        // Add transaction
        try {
            financialTransactionDAO.addTransaction(transaction); // Add transaction
            System.out.println("Transaction added: " + transaction);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Test getting a transaction by ID
        System.out.println("---- Testing Get Transaction by ID ----");
        FinancialTransaction retrievedTransaction = null;
        try {
            retrievedTransaction = financialTransactionDAO.getTransaction(1);
            if (retrievedTransaction != null) {
                System.out.println("Retrieved Transaction: " + retrievedTransaction);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Test updating a transaction
        System.out.println("---- Testing Update Transaction ----");
        if (retrievedTransaction != null) {
            retrievedTransaction.setAmount(6000.50);
            try {
                financialTransactionDAO.updateTransaction(retrievedTransaction);
                System.out.println("Transaction updated: " + retrievedTransaction);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Test retrieving all transactions
        System.out.println("---- Testing Get All Transactions ----");
        try {
            System.out.println("All Transactions: " + financialTransactionDAO.getAllTransactions());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Test deleting a transaction
        /*System.out.println("---- Testing Delete Transaction ----");
        try {
            financialTransactionDAO.deleteTransaction(retrievedTransaction.getTransactionID());
            System.out.println("Transaction deleted successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
