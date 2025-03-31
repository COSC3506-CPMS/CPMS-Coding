package com.cpms.cpms.services;

import com.cpms.cpms.dao.InvoiceDAO;
import com.cpms.cpms.entities.Invoice;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InvoiceServiceTest {

    private InvoiceService invoiceService;
    private InvoiceDAO invoiceDAOMock;

    @Before
    public void setUp() throws Exception {
        invoiceDAOMock = mock(InvoiceDAO.class);
        invoiceService = new InvoiceService();
      
        java.lang.reflect.Field field = InvoiceService.class.getDeclaredField("invoiceDAO");
        field.setAccessible(true);
        field.set(invoiceService, invoiceDAOMock);
    }

    @Test
    public void testAddInvoice() {
        Invoice invoice = new Invoice();
        invoiceService.addInvoice(invoice);
        verify(invoiceDAOMock).addInvoice(invoice);
    }

    @Test
    public void testGetInvoiceById() {
        Invoice mockInvoice = new Invoice();
        when(invoiceDAOMock.getInvoice(1)).thenReturn(mockInvoice);

        Invoice result = invoiceService.getInvoiceById(1);
        assertEquals(mockInvoice, result);
    }

    @Test
    public void testGetAllInvoices() {
        List<Invoice> mockList = Arrays.asList(new Invoice(), new Invoice());
        when(invoiceDAOMock.getAllInvoices()).thenReturn(mockList);

        List<Invoice> result = invoiceService.getAllInvoices();
        assertEquals(2, result.size());
    }

    @Test
    public void testUpdateInvoice() {
        Invoice invoice = new Invoice();
        invoiceService.updateInvoice(invoice);
        verify(invoiceDAOMock).updateInvoice(invoice);
    }

    @Test
    public void testDeleteInvoice() {
        invoiceService.deleteInvoice(42);
        verify(invoiceDAOMock).deleteInvoice(42);
    }
}
