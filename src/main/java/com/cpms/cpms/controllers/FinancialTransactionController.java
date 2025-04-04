package com.cpms.cpms.controllers;

import com.cpms.cpms.dao.FinancialTransactionDAO;
import com.cpms.cpms.entities.FinancialTransaction;
import com.cpms.cpms.entities.Project;
import java.sql.Timestamp;

public class FinancialTransactionController {

    private FinancialTransactionDAO financialTransactionDAO;

    // Constructor that accepts FinancialTransactionDAO
    public FinancialTransactionController(FinancialTransactionDAO financialTransactionDAO) {
        this.financialTransactionDAO = financialTransactionDAO;
    }

    // Method to add a financial transaction
    public void addTransaction() {
        // Dummy example for adding a transaction
        Project mockProject = new Project();
        mockProject.setProjectID(1); // Assuming project ID 1 for the test

        FinancialTransaction transaction = new FinancialTransaction();
        transaction.setTransactionID(1);
        transaction.setProject(mockProject);
        transaction.setAmount(5000.75);
        transaction.setDate(new Timestamp(System.currentTimeMillis()));
        transaction.setType(FinancialTransaction.TransactionType.PAYMENT);

        // Calling DAO's addTransaction method
        financialTransactionDAO.addTransaction(transaction);
    }

    // Method to update a financial transaction
    public void updateTransaction(int transactionID) {
        // Fetch the transaction from DAO
        FinancialTransaction transaction = financialTransactionDAO.getTransaction(transactionID);
        if (transaction != null) {
            // Update transaction fields if necessary and then save back to DAO
            transaction.setAmount(6000.75);  // Example update
            financialTransactionDAO.updateTransaction(transaction);
        }
    }

    // Method to delete a financial transaction
    public void deleteTransaction(int transactionID) {
        // Deleting the transaction by calling DAO
        financialTransactionDAO.deleteTransaction(transactionID);
    }

    // Method to get all financial transactions
    public void getAllTransactions() {
        financialTransactionDAO.getAllTransactions();
    }
}
