package com.cpms.cpms.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "financial_transactions")  // Mapping the entity to the correct table name
public class FinancialTransaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Assuming TransactionID is auto-incremented
    @Column(name = "TransactionID")  // Explicitly mapping to the correct column
    private int transactionID; 

    @ManyToOne
    @JoinColumn(name = "ProjectID", referencedColumnName = "ProjectID", nullable = false) 
    private Project project;
    
    @Column(name = "Amount", nullable = false, precision = 12, scale = 2) 
    private double amount;
    
    @Column(name = "Date", nullable = false) 
    private Timestamp date;
    
    @Enumerated(EnumType.STRING) 
    @Column(name = "Type", nullable = false)
    private TransactionType type;

    // Getters and Setters

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
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

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    // Enum for TransactionType
    public enum TransactionType {
        EXPENSE, PAYMENT, REFUND, OTHER
    }
}
