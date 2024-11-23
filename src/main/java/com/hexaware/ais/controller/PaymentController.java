package com.hexaware.ais.controller;

import java.util.List;

import com.hexaware.ais.dto.PaymentDTO;
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
    @PostMapping("/create")
    public ResponseEntity<PaymentDTO> createPayment(@RequestBody PaymentDTO paymentDTO) {

        PaymentDTO createdPayment = paymentService.createPayment(paymentDTO);

        return ResponseEntity.ok(createdPayment);
    }

    // Get a payment by ID
    @GetMapping("/get/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable String paymentId) {

        PaymentDTO payment = paymentService.getPaymentById(paymentId);

        return ResponseEntity.ok(payment);
    }

    // Get all payments
    @GetMapping("/getall")
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {

        List<PaymentDTO> payments = paymentService.getAllPayments();

        return ResponseEntity.ok(payments);
    }

    // Get payments by proposal ID
    @GetMapping("/get/payment-by-proposal/{proposalId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByProposalId(@PathVariable String proposalId) {

        List<PaymentDTO> payments = paymentService.getPaymentsByProposalId(proposalId);

        return ResponseEntity.ok(payments);
    }

    // Update a payment
    @PutMapping("/update/{paymentId}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable String paymentId, @RequestBody PaymentDTO paymentDTO) {

        PaymentDTO updatedPayment = paymentService.updatePayment(paymentId, paymentDTO);

        return ResponseEntity.ok(updatedPayment);
    }

    // Delete a payment
    @DeleteMapping("/delete/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable String paymentId) {

        paymentService.deletePayment(paymentId);

        return ResponseEntity.ok("Payment deleted successfully");
    }

    // Process a payment
    @PostMapping("/process-pay/{proposalId}")
    public ResponseEntity<PaymentDTO> processPayment(@PathVariable String proposalId, @RequestParam double amount, @RequestParam String paymentMethod) {

        PaymentDTO payment = paymentService.processPayment(proposalId, amount, paymentMethod);

        return ResponseEntity.ok(payment);
    }
}