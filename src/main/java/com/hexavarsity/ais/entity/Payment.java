package com.hexavarsity.ais.entity;

import jakarta.persistence.*;
import java.util.Date;

// Payment Entity
@Entity
@Table(name = "Payment")
public class Payment {

    /************************************ Attributes ************************************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(name = "proposal_id", nullable = false)
    private Proposal proposal;

    @Column(nullable = false)
    private Double amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;

    private String receiptUrl;

    
    /************************************ Getters and Setters ************************************/

    // Getters and Setters for paymentId
    public Integer getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    // Getters and Setters for proposal
    public Proposal getProposal() {
        return proposal;
    }
    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    // Getters and Setters for amount
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    // Getters and Setters for date
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    // Getters and Setters for status
    public PaymentStatus getStatus() {
        return status;
    }
    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    // Getters and Setters for receiptUrl
    public String getReceiptUrl() {
        return receiptUrl;
    }
    public void setReceiptUrl(String receiptUrl) {
        this.receiptUrl = receiptUrl;
    }
}

// PaymentStatus Enum
enum PaymentStatus {
    PENDING, COMPLETED
}