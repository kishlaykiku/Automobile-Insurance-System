package com.hexaware.ais.service;

import java.util.List;

import com.hexaware.ais.dto.PaymentDTO;


/*
 * @Author: Kishlay Kumar
 * Interface: IPaymentService
 * Description: This interface defines the contract for payment-related operations
 */
public interface IPaymentService {

    /******************************************* Method Signatures *******************************************/

    // Create a new payment
    PaymentDTO createPayment(PaymentDTO paymentDTO);

    // Get a payment by ID
    PaymentDTO getPaymentById(String paymentId);

    // Get all payments
    List<PaymentDTO> getAllPayments();

    // Get payments by proposal ID
    List<PaymentDTO> getPaymentsByProposalId(String proposalId);

    // Update a payment
    PaymentDTO updatePayment(String paymentId, PaymentDTO payment);

    // Delete a payment
    void deletePayment(String paymentId);

    // Process a payment
    PaymentDTO processPayment(String proposalId, double amount, String paymentMethod);
}