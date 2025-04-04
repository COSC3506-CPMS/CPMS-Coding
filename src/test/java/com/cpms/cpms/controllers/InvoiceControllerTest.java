package com.cpms.cpms.controllers;

import com.cpms.cpms.entities.Invoice;
import com.cpms.cpms.services.InvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class InvoiceControllerTest {

    private InvoiceController invoiceController; // Controller under test
    private InvoiceService invoiceServiceMock; // Mocked InvoiceService

    @BeforeEach
    public void setUp() {
        invoiceServiceMock = Mockito.mock(InvoiceService.class); // Mock the InvoiceService
        invoiceController = new InvoiceController(invoiceServiceMock); // Inject the mock into the controller
    }

    @Test
    public void testAddInvoice() {
        // Arrange: Create dummy invoice data
        Invoice invoice = new Invoice();
        invoice.setInvoiceID(1);
        invoice.setAmount(500.00);
        invoice.setDate(Timestamp.valueOf("2025-04-01 12:00:00"));
        invoice.setStatus(Invoice.Status.UNPAID);
        invoice.setPaymentMethod(Invoice.PaymentMethod.CASH);
        invoice.setPaymentDetails("Test payment");

        // Act: Call the controller method
        invoiceController.addInvoice(invoice);

        // Assert: Verify the service's addInvoice method was called with the correct data
        verify(invoiceServiceMock, times(1)).addInvoice(any(Invoice.class));
    }

    @Test
    public void testGetInvoiceById() {
        // Arrange: Mock the service to return an invoice
        Invoice mockInvoice = new Invoice();
        mockInvoice.setInvoiceID(1);
        mockInvoice.setAmount(1000.00);

        when(invoiceServiceMock.getInvoiceById(1)).thenReturn(mockInvoice);

        // Act: Call the controller method
        Invoice result = invoiceController.getInvoiceById(1);

        // Assert: Verify the result and service call
        assertNotNull(result);
        assertEquals(1000.00, result.getAmount());
        verify(invoiceServiceMock, times(1)).getInvoiceById(1);
    }

    @Test
    public void testUpdateInvoice() {
        // Arrange: Create a dummy invoice object
        Invoice invoice = new Invoice();
        invoice.setInvoiceID(1);
        invoice.setAmount(2000.00);

        // Act: Call the controller method
        invoiceController.updateInvoice(invoice);

        // Assert: Verify the service's updateInvoice method was called
        verify(invoiceServiceMock, times(1)).updateInvoice(invoice);
    }

    @Test
    public void testDeleteInvoice() {
        // Act: Call the controller method
        invoiceController.deleteInvoice(2);

        // Assert: Verify the service's deleteInvoice method was called with the correct ID
        verify(invoiceServiceMock, times(1)).deleteInvoice(2);
    }

    @Test
    public void testGetAllInvoices() {
        // Arrange: Mock the service to return a list of invoices
        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceID(1);
        invoice1.setAmount(500.00);

        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceID(2);
        invoice2.setAmount(1500.00);

        List<Invoice> mockInvoices = Arrays.asList(invoice1, invoice2);
        when(invoiceServiceMock.getAllInvoices()).thenReturn(mockInvoices);

        // Act: Call the controller method
        List<Invoice> result = invoiceController.getAllInvoices();

        // Assert: Verify the result and service call
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(500.00, result.get(0).getAmount());
        assertEquals(1500.00, result.get(1).getAmount());
        verify(invoiceServiceMock, times(1)).getAllInvoices();
    }
}
