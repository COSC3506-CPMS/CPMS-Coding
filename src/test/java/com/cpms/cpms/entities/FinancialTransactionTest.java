package com.cpms.cpms.entities;

import org.junit.jupiter.api.Test; // JUnit 5 testing
import org.junit.jupiter.api.Assertions; // Assertions

import java.sql.Timestamp;

public class FinancialTransactionTest {

    @Test
    public void testFinancialTransactionSettersAndGetters() {
        // Create FinancialTransaction object
        FinancialTransaction transaction = new FinancialTransaction();

        // Test data
        int transactionID = 101;
        double amount = 5000.75;
        Timestamp date = new Timestamp(System.currentTimeMillis());
        FinancialTransaction.TransactionType type = FinancialTransaction.TransactionType.PAYMENT; // Correct reference

        // Mock Project object
        Project project = new Project();
        project.setProjectID(1);

        // Set values
        transaction.setTransactionID(transactionID);
        transaction.setAmount(amount);
        transaction.setDate(date);
        transaction.setType(type);
        transaction.setProject(project);

        // Validate using getters
        Assertions.assertEquals(transactionID, transaction.getTransactionID());
        Assertions.assertEquals(amount, transaction.getAmount());
        Assertions.assertEquals(date, transaction.getDate());
        Assertions.assertEquals(type, transaction.getType());
        Assertions.assertEquals(project, transaction.getProject());
    }
}
