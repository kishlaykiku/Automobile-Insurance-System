package com.hexaware.ais.dto;

import java.time.LocalDate;

import com.hexaware.ais.entity.Payment;
import com.hexaware.ais.entity.Proposal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;


/*
 * @Author: Kishlay Kumar
 * Class: PaymentDTO
 * Description: Data Transfer Object for Payment Entity
 */
public class PaymentDTO {

    /******************************************* Attributes *******************************************/

    private String paymentId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    private double amount;

    @NotNull(message = "Payment date is required")
    @PastOrPresent(message = "Payment date cannot be in the future")
    private LocalDate paymentDate;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;

    @NotBlank(message = "Status is required")
    private String status;

    private String proposalId;

    /******************************************* Constructors *******************************************/

    // Default Constructor
    public PaymentDTO() {

        super();
    }

    // Constructor to map Payment entity to PaymentDTO
    public PaymentDTO(Payment payment) {

        super();

        this.paymentId = payment.getPaymentId();
        this.amount = payment.getAmount();
        this.paymentDate = payment.getPaymentDate();
        this.paymentMethod = payment.getPaymentMethod();
        this.status = payment.getStatus();
        this.proposalId = payment.getProposal() != null ? payment.getProposal().getProposalId() : null;
    }

    /******************************************* Getters and Setters *******************************************/

    public String getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getProposalId() {
        return proposalId;
    }
    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    /******************************************* Utility Methods *******************************************/

    // Convert DTO to Entity
    public Payment toEntity(Proposal proposal) {

        Payment payment = new Payment();

        payment.setPaymentId(this.paymentId);
        payment.setAmount(this.amount);
        payment.setPaymentDate(this.paymentDate);
        payment.setPaymentMethod(this.paymentMethod);
        payment.setStatus(this.status);
        payment.setProposal(proposal);

        return payment;
    }
}