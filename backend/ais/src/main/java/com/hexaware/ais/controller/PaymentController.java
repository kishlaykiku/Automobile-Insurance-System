package com.hexaware.ais.controller;

import java.util.List;

import com.hexaware.ais.dto.PaymentDTO;
import com.hexaware.ais.service.IPaymentService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    // Process (Create) a payment (User Only)
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/process-pay/{proposalId}")
    public ResponseEntity<PaymentDTO> processPayment(@PathVariable String proposalId, @RequestParam double amount, @RequestParam String paymentMethod) {

        PaymentDTO payment = paymentService.processPayment(proposalId, amount, paymentMethod);

        return ResponseEntity.ok(payment);
    }

    // Get a payment by ID (User and Officer)
    @PreAuthorize("hasAnyRole('USER', 'OFFICER')")
    @GetMapping("/get/{paymentId}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable String paymentId) {

        PaymentDTO payment = paymentService.getPaymentById(paymentId);

        return ResponseEntity.ok(payment);
    }

    // Get all payments (Officer Only)
    @PreAuthorize("hasRole('OFFICER')")
    @GetMapping("/getall")
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {

        List<PaymentDTO> payments = paymentService.getAllPayments();

        return ResponseEntity.ok(payments);
    }

    // Get payments by proposal ID (User and Officer)
    @PreAuthorize("hasAnyRole('USER', 'OFFICER')")
    @GetMapping("/get/payment-by-proposal/{proposalId}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByProposalId(@PathVariable String proposalId) {

        List<PaymentDTO> payments = paymentService.getPaymentsByProposalId(proposalId);

        return ResponseEntity.ok(payments);
    }

    // Update a payment (User Only)
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/{paymentId}")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable String paymentId, @Valid @RequestBody PaymentDTO paymentDTO) {

        PaymentDTO updatedPayment = paymentService.updatePayment(paymentId, paymentDTO);

        return ResponseEntity.ok(updatedPayment);
    }

    // Delete a payment (User Only)
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable String paymentId) {

        paymentService.deletePayment(paymentId);

        return ResponseEntity.ok("Payment deleted successfully");
    }
}