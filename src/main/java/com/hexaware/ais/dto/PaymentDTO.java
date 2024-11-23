package com.hexaware.ais.dto;

import java.time.LocalDate;

import com.hexaware.ais.entity.Payment;

/*
 * @Author: Kishlay Kumar
 * Class: PaymentDTO
 * Description: Data Transfer Object for Payment Entity
 */
public class PaymentDTO {

    /******************************************* Attributes *******************************************/

    private String paymentId;
    private double amount;
    private LocalDate paymentDate;
    private String paymentMethod;
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
}