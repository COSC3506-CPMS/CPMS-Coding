package com.cpms.cpms.entities;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Invoice {
	@Id
    private int invoiceID;  //invoice id as primary key
	
	@ManyToOne
    @JoinColumn(name = "contractorID", nullable = false) //contractor id as foreign key
    private Contractor contractor;
	
	@ManyToOne
    @JoinColumn(name = "projectID", nullable = false) //project id as foreign key
    private Project project; 
	
	@Column(nullable = false, precision = 12, scale = 2) //amount 
    private double amount;

	 @Column(nullable = false)
	 private Timestamp date;   //date of invoice
	 
	 @Enumerated(EnumType.STRING) //Enum for invoice status stored as string.
	 @Column(nullable = false)
	 private Status status;
	 
	 @Enumerated(EnumType.STRING) //Enum for payment method stored as string.
	 @Column
	 private PaymentMethod paymentMethod;
	 
	 @Column 
	 private String paymentDetails; //Additional details about the payment

	 @Column(nullable = false, precision = 12, scale = 2) 
	 private double outstandingBalance; //Outstanding balance for the invoice

	 //Getters and setters
	 //invoice id
	 public int getInvoiceID() {
	     return invoiceID;
	 }
	 public void setInvoiceID(int invoiceID) {
	     this.invoiceID = invoiceID;
	 }
	 
	 //contractor id
	 public Contractor getContractor() {
	     return contractor;
	 }
	 public void setContractor(Contractor contractor) {
	      this.contractor = contractor;
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

     //date
     public Timestamp getDate() {
         return date;
     }
     public void setDate(Timestamp date) {
         this.date = date;
     }
     
     //status
     public Status getStatus() {
         return status;
     }
     public void setStatus(Status status) {
         this.status = status;
     }
     
     //payment method
     public PaymentMethod getPaymentMethod() {
         return paymentMethod;
     }
     public void setPaymentMethod(PaymentMethod paymentMethod) {
         this.paymentMethod = paymentMethod;
     }
     
     //payment details
     public String getPaymentDetails() {
         return paymentDetails;
     }
     public void setPaymentDetails(String paymentDetails) {
         this.paymentDetails = paymentDetails;
     }
     
     //outstanding balance
     public double getOutstandingBalance() {
         return outstandingBalance;
     }
     public void setOutstandingBalance(double outstandingBalance) {
         this.outstandingBalance = outstandingBalance;
     }
 }

//enum for status and payment methods
enum Status {
    UNPAID, PAID, PARTIALLY_PAID, CANCELLED 
}
enum PaymentMethod {
    CASH, CREDIT_CARD, BANK_TRANSFER, OTHER 
}

