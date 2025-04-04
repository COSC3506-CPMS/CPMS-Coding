package com.cpms.cpms.services;

import com.cpms.cpms.dao.FinancialTransactionDAO;
import com.cpms.cpms.entities.FinancialTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FinancialTransactionServiceTest {

    private FinancialTransactionService financialTransactionService; // Service under test
    private FinancialTransactionDAO financialTransactionDAOMock; // Mocked DAO dependency

    @BeforeEach
    public void setUp() {
        financialTransactionDAOMock = Mockito.mock(FinancialTransactionDAO.class); // Mock the DAO
        financialTransactionService = new FinancialTransactionService(); // Create service instance
        financialTransactionService.setTransactionDAO(financialTransactionDAOMock); // Inject mock DAO into the service
    }

    @Test
    public void shouldAddTransaction() {
        FinancialTransaction transaction = new FinancialTransaction();
        transaction.setTransactionID(1);
        transaction.setAmount(5000.75);
        transaction.setType(FinancialTransaction.TransactionType.PAYMENT);

        financialTransactionService.addTransaction(transaction);

        verify(financialTransactionDAOMock, times(1)).addTransaction(transaction); // Verify DAO interaction
    }

    @Test
    public void shouldGetTransactionById() {
        FinancialTransaction transaction = new FinancialTransaction();
        transaction.setTransactionID(1);
        transaction.setAmount(5000.75);
        transaction.setType(FinancialTransaction.TransactionType.PAYMENT);

        when(financialTransactionDAOMock.getTransaction(1)).thenReturn(transaction); // Mock DAO behavior

        FinancialTransaction result = financialTransactionService.getTransactionById(1);

        assertNotNull(result);
        assertEquals(1, result.getTransactionID());
        assertEquals(5000.75, result.getAmount());
        verify(financialTransactionDAOMock, times(1)).getTransaction(1); // Verify DAO interaction
    }

    @Test
    public void shouldGetAllTransactions() {
        FinancialTransaction transaction1 = new FinancialTransaction();
        transaction1.setTransactionID(1);
        transaction1.setAmount(5000.75);
        transaction1.setType(FinancialTransaction.TransactionType.PAYMENT);

        FinancialTransaction transaction2 = new FinancialTransaction();
        transaction2.setTransactionID(2);
        transaction2.setAmount(6000.50);
        transaction2.setType(FinancialTransaction.TransactionType.PAYMENT);

        List<FinancialTransaction> mockList = Arrays.asList(transaction1, transaction2);
        when(financialTransactionDAOMock.getAllTransactions()).thenReturn(mockList); // Mock DAO behavior

        List<FinancialTransaction> result = financialTransactionService.getAllTransactions();

        assertEquals(2, result.size());
        assertEquals(5000.75, result.get(0).getAmount());
        assertEquals(6000.50, result.get(1).getAmount());
        verify(financialTransactionDAOMock, times(1)).getAllTransactions(); // Verify DAO interaction
    }

    @Test
    public void shouldUpdateTransaction() {
        FinancialTransaction transaction = new FinancialTransaction();
        transaction.setTransactionID(1);
        transaction.setAmount(5000.75);
        transaction.setType(FinancialTransaction.TransactionType.PAYMENT);

        financialTransactionService.updateTransaction(transaction);

        verify(financialTransactionDAOMock, times(1)).updateTransaction(transaction); // Verify DAO interaction
    }

    @Test
    public void shouldDeleteTransactionById() {
        financialTransactionService.deleteTransaction(1);

        verify(financialTransactionDAOMock, times(1)).deleteTransaction(1); // Verify DAO interaction
    }
}
