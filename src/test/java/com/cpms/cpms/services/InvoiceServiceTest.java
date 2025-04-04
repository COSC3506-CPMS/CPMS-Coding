package com.cpms.cpms.services;

import com.cpms.cpms.dao.InvoiceDAO;
import com.cpms.cpms.entities.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class InvoiceServiceTest {

    private InvoiceService invoiceService; // Service under test
    private InvoiceDAO invoiceDAOMock; // Mocked DAO dependency

    @BeforeEach
    public void setUp() {
        invoiceDAOMock = Mockito.mock(InvoiceDAO.class); // Mock the DAO
        invoiceService = new InvoiceService(invoiceDAOMock); // Inject mock DAO into the service
    }

    @Test
    public void shouldAddInvoice() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber("INV-123");

        invoiceService.addInvoice(invoice);

        verify(invoiceDAOMock, times(1)).addInvoice(invoice); // Verify DAO interaction
    }

    @Test
    public void shouldGetInvoiceById() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber("INV-123");

        when(invoiceDAOMock.getInvoice(1)).thenReturn(invoice); // Mock DAO behavior

        Invoice result = invoiceService.getInvoiceById(1);

        assertNotNull(result);
        assertEquals("INV-123", result.getInvoiceNumber());
        verify(invoiceDAOMock, times(1)).getInvoice(1); // Verify DAO interaction
    }

    @Test
    public void shouldUpdateInvoice() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceID(1);
        invoice.setInvoiceNumber("INV-456");

        invoiceService.updateInvoice(invoice);

        verify(invoiceDAOMock, times(1)).updateInvoice(invoice); // Verify DAO interaction
    }

    @Test
    public void shouldDeleteInvoiceById() {
        invoiceService.deleteInvoice(5);

        verify(invoiceDAOMock, times(1)).deleteInvoice(5); // Verify DAO interaction
    }

    @Test
    public void shouldGetAllInvoices() {
        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceNumber("INV-101");
        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceNumber("INV-102");

        List<Invoice> mockList = Arrays.asList(invoice1, invoice2);
        when(invoiceDAOMock.getAllInvoices()).thenReturn(mockList); // Mock DAO behavior

        List<Invoice> result = invoiceService.getAllInvoices();

        assertEquals(2, result.size());
        assertEquals("INV-101", result.get(0).getInvoiceNumber());
        assertEquals("INV-102", result.get(1).getInvoiceNumber());
        verify(invoiceDAOMock, times(1)).getAllInvoices(); // Verify DAO interaction
    }
}
