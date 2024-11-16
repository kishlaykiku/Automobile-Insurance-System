package com.hexaware.ais.service;

import java.util.List;

import com.hexaware.ais.entity.Payment;


/*
 * @Author: Kishlay Kumar
 * Interface: IPaymentService
 * Description: This interface defines the contract for payment-related operations
 */
public interface IPaymentService {

    /******************************************* Method Signatures *******************************************/

    // Create a new payment
    Payment createPayment(Payment payment);

    // Get a payment by ID
    Payment getPaymentById(String paymentId);

    // Get all payments
    List<Payment> getAllPayments();

    // Get payments by proposal ID
    List<Payment> getPaymentsByProposalId(String proposalId);

    // Update a payment
    Payment updatePayment(String paymentId, Payment payment);

    // Delete a payment
    void deletePayment(String paymentId);
}