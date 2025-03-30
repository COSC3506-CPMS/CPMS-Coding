package com.cpms.cpms.controllers;

import com.cpms.cpms.entities.FinancialTransaction;
import com.cpms.cpms.services.FinancialTransactionService;
import java.util.List;

public class FinancialTransactionController {
    private FinancialTransactionService transactionService;

    public FinancialTransactionController() {
        this.transactionService = new FinancialTransactionService();
    }

    //Adds a new financial transaction
    public void addTransaction(FinancialTransaction transaction) {
        transactionService.addTransaction(transaction);
    }

    //Retrieves a transaction by its ID
    public FinancialTransaction getTransactionById(int id) {
        return transactionService.getTransactionById(id);
    }

    //Retrieves all transactions
    public List<FinancialTransaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    //Updates an existing transaction
    public void updateTransaction(FinancialTransaction transaction) {
        transactionService.updateTransaction(transaction);
    }

    //Deletes a transaction by its ID
    public void deleteTransaction(int id) {
        transactionService.deleteTransaction(id);
    }
}