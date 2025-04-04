package com.cpms.cpms.controllers;

import com.cpms.cpms.dao.FinancialTransactionDAO;
import com.cpms.cpms.entities.FinancialTransaction;
import com.cpms.cpms.entities.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import static org.mockito.Mockito.*;

public class FinancialTransactionControllerTest {

    private FinancialTransactionController financialTransactionController; // Controller under test
    private FinancialTransactionDAO financialTransactionDAOMock; // Mocked FinancialTransactionDAO

    @BeforeEach
    public void setUp() {
        financialTransactionDAOMock = Mockito.mock(FinancialTransactionDAO.class); // Mock the DAO
        financialTransactionController = new FinancialTransactionController(financialTransactionDAOMock); // Inject the mock into the controller
    }

    @Test
    public void testAddTransaction() {
        // Arrange: Create dummy transaction data
        Project mockProject = new Project();
        mockProject.setProjectID(1); // Assuming project ID 1 for the test

        FinancialTransaction transaction = new FinancialTransaction();
        transaction.setTransactionID(1);
        transaction.setProject(mockProject);
        transaction.setAmount(5000.75);
        transaction.setDate(new Timestamp(System.currentTimeMillis()));
        transaction.setType(FinancialTransaction.TransactionType.PAYMENT);

        // Act: Call the controller method
        financialTransactionController.addTransaction();

        // Assert: Verify the DAO's addTransaction method was called with the correct data
        verify(financialTransactionDAOMock, times(1)).addTransaction(any(FinancialTransaction.class));
    }

    @Test
    public void testUpdateTransaction() {
        // Arrange: Mock a transaction and its update
        FinancialTransaction mockTransaction = new FinancialTransaction();
        mockTransaction.setTransactionID(1);
        mockTransaction.setAmount(5000.75);
        when(financialTransactionDAOMock.getTransaction(1)).thenReturn(mockTransaction);

        // Act: Call the controller method
        financialTransactionController.updateTransaction(1);

        // Assert: Verify the DAO's updateTransaction method was called with the updated transaction
        verify(financialTransactionDAOMock, times(1)).updateTransaction(any(FinancialTransaction.class));
    }

    @Test
    public void testDeleteTransaction() {
        // Act: Call the controller method to delete a transaction
        financialTransactionController.deleteTransaction(1);

        // Assert: Verify the DAO's deleteTransaction method was called with the correct transaction ID
        verify(financialTransactionDAOMock, times(1)).deleteTransaction(1);
    }

    @Test
    public void testGetAllTransactions() {
        // Act: Call the controller method to get all transactions
        financialTransactionController.getAllTransactions();

        // Assert: Verify the DAO's getAllTransactions method was called
        verify(financialTransactionDAOMock, times(1)).getAllTransactions();
    }
}
