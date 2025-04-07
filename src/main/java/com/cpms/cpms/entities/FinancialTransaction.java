package com.cpms.cpms.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "financialtransactions")  // Mapping the entity to the correct table name
public class FinancialTransaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Assuming TransactionID is auto-incremented
    @Column(name = "TransactionID")  // Explicitly mapping to the correct column
    private int transactionID; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TransactionProjectID", nullable = false)
    private Project transactionProjectID;
   
    
    @Column(name = "TransactionAmount", nullable = false, precision = 12, scale = 2) 
    private double transactionAmount;
    
    @Column(name = "TransactionDate", nullable = false) 
    private LocalDateTime transactionDate;
    
    @Enumerated(EnumType.STRING) 
    @Column(name = "TransactionType", nullable = false)
    private TransactionType transactionType;

    // Getters and Setters

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public Project getTransactionProjectID() {
        return transactionProjectID;
    }

    public void setTrasactionProject(Project transactionProjectID) {
        this.transactionProjectID = transactionProjectID;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    // Enum for TransactionType
    public enum TransactionType {
        EXPENSE, PAYMENT, REFUND, OTHER
    }
}

