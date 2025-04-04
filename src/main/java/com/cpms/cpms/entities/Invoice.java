package com.cpms.cpms.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invoiceID;

    @ManyToOne
    @JoinColumn(name = "contractorID", nullable = false)
    private Contractor contractor;

    @ManyToOne
    @JoinColumn(name = "projectID", nullable = false)
    private Project project;

    @Column(nullable = false, precision = 12, scale = 2)
    private double amount;

    @Column(nullable = false)
    private Timestamp date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column
    private PaymentMethod paymentMethod;

    @Lob
    @Column
    private String paymentDetails;

    @Column(nullable = false, precision = 12, scale = 2)
    private double outstandingBalance;

    // Added the invoiceNumber field and its getter/setter
    @Column(nullable = false, unique = true)
    private String invoiceNumber;

    // Getters and setters...

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(double outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    // Getter and setter for invoiceNumber
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    // **Make Enums Public**
    public enum Status {
        UNPAID, PAID, PARTIALLY_PAID, CANCELLED
    }

    public enum PaymentMethod {
        CASH, CREDIT_CARD, BANK_TRANSFER, OTHER
    }
}
