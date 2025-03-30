package com.cpms.cpms.entities;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class FinancialTransaction {
	@Id
	private int transactionID; //transaction if as primary key

	@ManyToOne
    @JoinColumn(name = "projectID", nullable = false) //project id as foreign key
    private Project project;
	
	@Column(nullable = false, precision = 12, scale = 2) //Transaction amount
    private double amount;
	
	 @Column(nullable = false) // Date of the transaction.
	 private Timestamp date;
	 
	 @Enumerated(EnumType.STRING) // Enum for transaction type stored as a string.
	 @Column(nullable = false)
	 private TransactionType type;
	 
	// Getters and setters
	//transaction id
	public int getTransactionID() {
	    return transactionID;
	}
	public void setTransactionID(int transactionID) {
	    this.transactionID = transactionID;
	}
	
	//project id
	public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    
    //amount
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    //date of transaction
    public Timestamp getDate() {
        return date;
    }
    public void setDate(Timestamp date) {
        this.date = date;
    }
    
    //type of transaction
    public TransactionType getType() {
        return type;
    }
    public void setType(TransactionType type) {
        this.type = type;
    }
}

//enum for transaction type
enum TransactionType {
    EXPENSE, PAYMENT, REFUND, OTHER 
}














