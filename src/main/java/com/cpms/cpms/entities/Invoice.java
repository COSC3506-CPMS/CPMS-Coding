package com.cpms.cpms.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invoiceID;

    @ManyToOne
    @JoinColumn(name = "invoiceProjectID", nullable = false)
    private Project invoiceProjectID;

    @Column(nullable = false, precision = 12, scale = 2)
    private double amount;

    @Column(nullable = false)
    private LocalDateTime date; // Changed to LocalDateTime

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

    // Getters and setters...

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Project getInvoiceProjectID() {
        return invoiceProjectID;
    }

    public void setInvoiceProjectID(Project invoiceProjectID) {
        this.invoiceProjectID = invoiceProjectID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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

    // Enums remain public
    public enum Status {
        UNPAID, PAID, PARTIALLY_PAID, CANCELLED
    }

    public enum PaymentMethod {
        CASH, CREDIT_CARD, BANK_TRANSFER, OTHER
    }
}