package com.hexaware.ais.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


/*
 * @Author: Kishlay Kumar
 * Class: Payment Entity
 * Description: This class is used to represent the Payment entity
 * Map: It is mapped to the `payment` table in the database
 */
@Entity
@Table(name = "payment")
public class Payment {

    /******************************************* Attributes *******************************************/

    @Id
    @Column(name = "payment_id", nullable = false, unique = true)
    private String paymentId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    @Column(name = "amount", nullable = false)
    private double amount;

    @NotNull(message = "Payment date is required")
    @PastOrPresent(message = "Payment date cannot be in the future")
    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @NotBlank(message = "Payment method is required")
    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @NotBlank(message = "Status is required")
    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposal_id", nullable = false)
    private Proposal proposal;

    @PrePersist
    protected void generateId() {

        if (this.paymentId == null) {

            this.paymentId = UUID.randomUUID().toString();
        }

        if(this.status == null) {

            this.status = "Pending";
        }

        this.paymentDate = LocalDate.now();
        
    }

    /******************************************* Getters and Setters *******************************************/

    // Getter and Setter for paymentId
    public String getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    // Getter and Setter for amount
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Getter and Setter for paymentDate
    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    // Getter and Setter for paymentMethod
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and Setter for proposal
    public Proposal getProposal() {
        return proposal;
    }
    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }
}