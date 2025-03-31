package com.cpms.cpms.dao;

import com.cpms.cpms.config.HibernateUtil;
import com.cpms.cpms.entities.FinancialTransaction;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class FinancialTransactionDAOTest {

    private static FinancialTransactionDAO transactionDAO;

    @BeforeClass
    public static void setUp() {
        transactionDAO = new FinancialTransactionDAO();
    }

    @Test
    public void testAddAndGetTransaction() {
        FinancialTransaction transaction = new FinancialTransaction();
        transaction.setTransactionID(1);
        transaction.setAmount(5000.0);
        transaction.setDate(Date.valueOf("2025-04-01"));
        transaction.setDescription("Project startup funding");
        transaction.setType("Credit");

        transactionDAO.addTransaction(transaction);

        FinancialTransaction result = transactionDAO.getTransaction(1);
        assertNotNull(result);
        assertEquals(5000.0, result.getAmount(), 0.01);
    }

    @Test
    public void testUpdateTransaction() {
        FinancialTransaction transaction = transactionDAO.getTransaction(1);
        transaction.setAmount(7500.0);
        transactionDAO.updateTransaction(transaction);

        FinancialTransaction updated = transactionDAO.getTransaction(1);
        assertEquals(7500.0, updated.getAmount(), 0.01);
    }

    @Test
    public void testGetAllTransactions() {
        List<FinancialTransaction> transactions = transactionDAO.getAllTransactions();
        assertTrue(transactions.size() >= 1);
    }

    @Test
    public void testDeleteTransaction() {
        transactionDAO.deleteTransaction(1);
        FinancialTransaction deleted = transactionDAO.getTransaction(1);
        assertNull(deleted);
    }

    @AfterClass
    public static void tearDown() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        if (factory != null) factory.close();
    }
}
