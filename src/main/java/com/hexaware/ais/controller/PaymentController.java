package com.hexaware.ais.controller;

import java.util.List;

import com.hexaware.ais.entity.Payment;
import com.hexaware.ais.service.IPaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/*
 * @Author: Kishlay Kumar
 * Class: PaymentController
 * Description: This class handles HTTP requests related to payment operations
 */
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    /******************************************* Dependencies *******************************************/

    @Autowired
    private IPaymentService paymentService;

    /******************************************* Endpoints *******************************************/

    // Create a new payment
    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {

        Payment createdPayment = paymentService.createPayment(payment);

        return ResponseEntity.ok(createdPayment);
    }

    // Get a payment by ID
    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable String paymentId) {

        Payment payment = paymentService.getPaymentById(paymentId);

        return ResponseEntity.ok(payment);
    }

    // Get all payments
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {

        List<Payment> payments = paymentService.getAllPayments();

        return ResponseEntity.ok(payments);
    }

    // Get payments by proposal ID
    @GetMapping("/proposal/{proposalId}")
    public ResponseEntity<List<Payment>> getPaymentsByProposalId(@PathVariable String proposalId) {

        List<Payment> payments = paymentService.getPaymentsByProposalId(proposalId);

        return ResponseEntity.ok(payments);
    }

    // Update a payment
    @PutMapping("/{paymentId}")
    public ResponseEntity<Payment> updatePayment(@PathVariable String paymentId, @RequestBody Payment payment) {

        Payment updatedPayment = paymentService.updatePayment(paymentId, payment);

        return ResponseEntity.ok(updatedPayment);
    }

    // Delete a payment
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable String paymentId) {

        paymentService.deletePayment(paymentId);

        return ResponseEntity.ok("Payment deleted successfully");
    }
}