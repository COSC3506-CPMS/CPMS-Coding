package com.cpms.cpms.entities;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;

public class InvoiceTest {

    @Test
    public void testInvoiceSettersAndGetters() {
        Invoice invoice = new Invoice();

        int invoiceID = 101;
        double amount = 2500.75;
        double outstanding = 500.25;
        Timestamp date = Timestamp.valueOf("2025-04-15 10:00:00");
        Status status = Status.PARTIALLY_PAID;
        PaymentMethod method = PaymentMethod.BANK_TRANSFER;
        String paymentDetails = "Transaction #12345";

        // Contractor mock
        Contractor contractor = new Contractor();
        contractor.setContractorID(201);
        contractor.setContractorName("XYZ Contracting");

        // Project mock
        Project project = new Project();
        project.setProjectID(301);
        project.setProjectName("Bridge Renovation");

        // Set fields
        invoice.setInvoiceID(invoiceID);
        invoice.setAmount(amount);
        invoice.setOutstandingBalance(outstanding);
        invoice.setDate(date);
        invoice.setStatus(status);
        invoice.setPaymentMethod(method);
        invoice.setPaymentDetails(paymentDetails);
        invoice.setContractor(contractor);
        invoice.setProject(project);

        // Assertions
        Assert.assertEquals(invoiceID, invoice.getInvoiceID());
        Assert.assertEquals(amount, invoice.getAmount(), 0.001);
        Assert.assertEquals(outstanding, invoice.getOutstandingBalance(), 0.001);
        Assert.assertEquals(date, invoice.getDate());
        Assert.assertEquals(status, invoice.getStatus());
        Assert.assertEquals(method, invoice.getPaymentMethod());
        Assert.assertEquals(paymentDetails, invoice.getPaymentDetails());
        Assert.assertEquals(contractor, invoice.getContractor());
        Assert.assertEquals(project, invoice.getProject());
    }
}
