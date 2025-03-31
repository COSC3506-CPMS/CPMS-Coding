package com.cpms.cpms.services;

import com.cpms.cpms.dao.FinancialTransactionDAO;
import com.cpms.cpms.entities.FinancialTransaction;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FinancialTransactionServiceTest {

    private FinancialTransactionService transactionService;
    private FinancialTransactionDAO transactionDAOMock;

    @Before
    public void setUp() throws Exception {
        transactionDAOMock = mock(FinancialTransactionDAO.class);
        transactionService = new FinancialTransactionService();

        java.lang.reflect.Field field = FinancialTransactionService.class.getDeclaredField("transactionDAO");
        field.setAccessible(true);
        field.set(transactionService, transactionDAOMock);
    }

    @Test
    public void testAddTransaction() {
        FinancialTransaction transaction = new FinancialTransaction();
        transactionService.addTransaction(transaction);
        verify(transactionDAOMock).addTransaction(transaction);
    }

    @Test
    public void testGetTransactionById() {
        FinancialTransaction mockTransaction = new FinancialTransaction();
        when(transactionDAOMock.getTransaction(1)).thenReturn(mockTransaction);

        FinancialTransaction result = transactionService.getTransactionById(1);
        assertEquals(mockTransaction, result);
    }

    @Test
    public void testGetAllTransactions() {
        List<FinancialTransaction> mockList = Arrays.asList(new FinancialTransaction(), new FinancialTransaction());
        when(transactionDAOMock.getAllTransactions()).thenReturn(mockList);

        List<FinancialTransaction> result = transactionService.getAllTransactions();
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateTransaction() {
        FinancialTransaction transaction = new FinancialTransaction();
        transactionService.updateTransaction(transaction);
        verify(transactionDAOMock).updateTransaction(transaction);
    }

    @Test
    public void testDeleteTransaction() {
        transactionService.deleteTransaction(5);
        verify(transactionDAOMock).deleteTransaction(5);
    }
}
