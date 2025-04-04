package com.cpms.cpms.dao;

import com.cpms.cpms.entities.Invoice;
import com.cpms.cpms.entities.Contractor;
import com.cpms.cpms.entities.Project;

import java.sql.Timestamp;

public class InvoiceDAOTest {
    public static void main(String[] args) {
        InvoiceDAO invoiceDAO = new InvoiceDAO();

        // Test adding an invoice
        System.out.println("---- Testing Add Invoice ----");
        Contractor contractor = new Contractor();
        contractor.setContractorID(1); // Example contractor ID

        Project project = new Project();
        project.setProjectID(1); // Example project ID

        Invoice invoice = new Invoice();
        invoice.setContractor(contractor);
        invoice.setProject(project);
        invoice.setAmount(200000.00); // Example amount
        invoice.setDate(Timestamp.valueOf("2025-04-01 10:00:00")); // Example date
        invoice.setStatus(Invoice.Status.UNPAID); // Example status
        invoice.setPaymentMethod(Invoice.PaymentMethod.CASH); // Example payment method
        invoice.setPaymentDetails("Initial payment for New System Launch"); // Example payment details
        invoice.setOutstandingBalance(200000.00); // Example outstanding balance

        invoiceDAO.addInvoice(invoice); // Add invoice
        System.out.println("Invoice added: " + invoice);

        // Test getting an invoice by ID
        System.out.println("---- Testing Get Invoice by ID ----");
        Invoice retrievedInvoice = invoiceDAO.getInvoice(invoice.getInvoiceID());
        if (retrievedInvoice != null) {
            System.out.println("Retrieved Invoice: " + retrievedInvoice);
        }

        // Test updating an invoice
        System.out.println("---- Testing Update Invoice ----");
        if (retrievedInvoice != null) {
            retrievedInvoice.setAmount(1800.50); // Example updated amount
            invoiceDAO.updateInvoice(retrievedInvoice);
            System.out.println("Invoice updated: " + retrievedInvoice);
        }

        // Test retrieving all invoices
        System.out.println("---- Testing Get All Invoices ----");
        System.out.println("All Invoices: " + invoiceDAO.getAllInvoices());

        // Test deleting an invoice
        System.out.println("---- Testing Delete Invoice ----");
        if (retrievedInvoice != null) {
            invoiceDAO.deleteInvoice(retrievedInvoice.getInvoiceID());
            System.out.println("Invoice deleted successfully!");
        }
    }
}