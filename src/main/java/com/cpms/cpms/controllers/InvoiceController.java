package com.cpms.cpms.controllers;

import com.cpms.cpms.entities.Invoice;
import com.cpms.cpms.services.InvoiceService;
import java.util.List;

public class InvoiceController {
    private InvoiceService invoiceService;

    public InvoiceController() {
        this.invoiceService = new InvoiceService();
    }

    //Adds a new invoice
    public void addInvoice(Invoice invoice) {
        invoiceService.addInvoice(invoice);
    }

    //Retrieves an invoice by its ID
    public Invoice getInvoiceById(int id) {
        return invoiceService.getInvoiceById(id);
    }

    //Retrieves all invoices
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }
    

    //Updates an existing invoice
    public void updateInvoice(Invoice invoice) {
        invoiceService.updateInvoice(invoice);
    }

    //Deletes an invoice by its ID
    public void deleteInvoice(int id) {
        invoiceService.deleteInvoice(id);
    }
}