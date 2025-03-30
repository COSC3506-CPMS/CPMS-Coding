package com.cpms.cpms.services;

import com.cpms.cpms.dao.InvoiceDAO;
import com.cpms.cpms.entities.Invoice;
import java.util.List;

public class InvoiceService {
    private InvoiceDAO invoiceDAO;

    public InvoiceService() {
        this.invoiceDAO = new InvoiceDAO();
    }

    // Adds a new invoice to the database
    public void addInvoice(Invoice invoice) {
        invoiceDAO.addInvoice(invoice);
    }

    // Retrieves an invoice by its ID
    public Invoice getInvoiceById(int id) {
        return invoiceDAO.getInvoice(id);
    }

    // Retrieves all invoices from the database
    public List<Invoice> getAllInvoices() {
        return invoiceDAO.getAllInvoices();
    }

    // Updates an existing invoice's details
    public void updateInvoice(Invoice invoice) {
        invoiceDAO.updateInvoice(invoice);
    }

    // Deletes an invoice by its ID
    public void deleteInvoice(int id) {
        invoiceDAO.deleteInvoice(id);
    }
}