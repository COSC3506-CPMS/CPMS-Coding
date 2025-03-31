package com.cpms.cpms.dao;

import com.cpms.cpms.config.HibernateUtil;
import com.cpms.cpms.entities.Invoice;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class InvoiceDAOTest {

    private static InvoiceDAO invoiceDAO;

    @BeforeClass
    public static void setUp() {
        invoiceDAO = new InvoiceDAO();
    }

    @Test
    public void testAddAndGetInvoice() {
        Invoice invoice = new Invoice();
        invoice.setInvoiceID(1);
        invoice.setIssueDate(Date.valueOf("2025-04-01"));
        invoice.setAmount(10000.0);
        invoice.setStatus("Issued");

        invoiceDAO.addInvoice(invoice);

        Invoice result = invoiceDAO.getInvoice(1);
        assertNotNull(result);
        assertEquals(10000.0, result.getAmount(), 0.01);
    }

    @Test
    public void testUpdateInvoice() {
        Invoice invoice = invoiceDAO.getInvoice(1);
        invoice.setStatus("Paid");
        invoiceDAO.updateInvoice(invoice);

        Invoice updated = invoiceDAO.getInvoice(1);
        assertEquals("Paid", updated.getStatus());
    }

    @Test
    public void testGetAllInvoices() {
        List<Invoice> invoices = invoiceDAO.getAllInvoices();
        assertTrue(invoices.size() >= 1);
    }

    @Test
    public void testDeleteInvoice() {
        invoiceDAO.deleteInvoice(1);
        Invoice deleted = invoiceDAO.getInvoice(1);
        assertNull(deleted);
    }

    @AfterClass
    public static void tearDown() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        if (factory != null) factory.close();
    }
}
