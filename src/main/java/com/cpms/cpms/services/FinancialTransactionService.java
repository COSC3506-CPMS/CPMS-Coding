package com.cpms.cpms.services;

import com.cpms.cpms.dao.FinancialTransactionDAO;
import com.cpms.cpms.entities.FinancialTransaction;
import java.util.List;

public class FinancialTransactionService {
    private FinancialTransactionDAO transactionDAO;

    public FinancialTransactionService() {
        this.transactionDAO = new FinancialTransactionDAO();
    }

    // Adds a new financial transaction to the database
    public void addTransaction(FinancialTransaction transaction) {
        transactionDAO.addTransaction(transaction);
    }

    // Retrieves a financial transaction by its ID
    public FinancialTransaction getTransactionById(int id) {
        return transactionDAO.getTransaction(id);
    }

    // Retrieves all financial transactions from the database
    public List<FinancialTransaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

    // Updates an existing financial transaction
    public void updateTransaction(FinancialTransaction transaction) {
        transactionDAO.updateTransaction(transaction);
    }

    // Deletes a financial transaction by its ID
    public void deleteTransaction(int id) {
        transactionDAO.deleteTransaction(id);
    }
}