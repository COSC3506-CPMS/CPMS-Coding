package com.cpms.cpms.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.sql.Timestamp;

public class InvoiceTest {

    @Test
    public void testInvoiceSettersAndGetters() {
        // Create a new Invoice object
        Invoice invoice = new Invoice();

        // Values to test
        int invoiceID = 1;
        double amount = 550000.00;
        Timestamp date = Timestamp.valueOf("2025-04-01 12:00:00");
        Invoice.Status status = Invoice.Status.UNPAID;  // Use Invoice.Status
        Invoice.PaymentMethod paymentMethod = Invoice.PaymentMethod.CASH;  // Use Invoice.PaymentMethod
        String paymentDetails = "Initial payment for New System Launch";
        double outstandingBalance = 550000.00;

        // Create mock objects for contractor and project (assuming they are set up correctly)
        Contractor contractor = new Contractor();
        contractor.setContractorID(1);
        Project project = new Project();
        project.setProjectID(1);

        // Set values using setter methods
        invoice.setInvoiceID(invoiceID);
        invoice.setAmount(amount);
        invoice.setDate(date);
        invoice.setStatus(status);
        invoice.setPaymentMethod(paymentMethod);
        invoice.setPaymentDetails(paymentDetails);
        invoice.setOutstandingBalance(outstandingBalance);
        invoice.setContractor(contractor);
        invoice.setProject(project);

        // Validate values using getter methods and assertions
        Assertions.assertEquals(invoiceID, invoice.getInvoiceID());
        Assertions.assertEquals(amount, invoice.getAmount());
        Assertions.assertEquals(date, invoice.getDate());
        Assertions.assertEquals(status, invoice.getStatus());
        Assertions.assertEquals(paymentMethod, invoice.getPaymentMethod());
        Assertions.assertEquals(paymentDetails, invoice.getPaymentDetails());
        Assertions.assertEquals(outstandingBalance, invoice.getOutstandingBalance());
        Assertions.assertEquals(contractor, invoice.getContractor());
        Assertions.assertEquals(project, invoice.getProject());
    }
}
