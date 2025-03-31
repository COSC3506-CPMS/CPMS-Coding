package com.cpms.cpms.entities;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;

public class FinancialTransactionTest {

    @Test
    public void testSettersAndGetters() {
        FinancialTransaction transaction = new FinancialTransaction();

        int transactionID = 1;
        double amount = 1200.75;
        Timestamp date = Timestamp.valueOf("2025-04-01 10:30:00");
        TransactionType type = TransactionType.EXPENSE;

        Project project = new Project();
        project.setProjectID(100);
        project.setProjectName("Highway Extension");
        project.setProjectBudget(1000000.00);
        project.setStatus("In Progress");

        transaction.setTransactionID(transactionID);
        transaction.setAmount(amount);
        transaction.setDate(date);
        transaction.setType(type);
        transaction.setProject(project);

        Assert.assertEquals(transactionID, transaction.getTransactionID());
        Assert.assertEquals(amount, transaction.getAmount(), 0.001);
        Assert.assertEquals(date, transaction.getDate());
        Assert.assertEquals(type, transaction.getType());
        Assert.assertEquals(project, transaction.getProject());
    }
}
