package com.hexaware.ais.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.hexaware.ais.entity.Payment;
import com.hexaware.ais.entity.Proposal;
import com.hexaware.ais.repository.PaymentRepository;
import com.hexaware.ais.repository.ProposalRepository;
import com.hexaware.ais.service.IPaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * @Author: Kishlay Kumar
 * Class: PaymentServiceImpl
 * Description: This class implements the IPaymentService interface for payment-related operations
 */
@Service
public class PaymentServiceImpl implements IPaymentService {

    /******************************************* Dependencies *******************************************/

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProposalRepository proposalRepository;

    /******************************************* Methods *******************************************/

    @Override
    public Payment createPayment(Payment payment) {

        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(String paymentId) {

        Optional<Payment> payment = paymentRepository.findById(paymentId);

        return payment.orElseThrow(() -> new RuntimeException("Payment not found with ID: " + paymentId));
    }

    @Override
    public List<Payment> getAllPayments() {

        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> getPaymentsByProposalId(String proposalId) {

        return paymentRepository.findByProposalProposalId(proposalId);
    }

    @Override
    public Payment updatePayment(String paymentId, Payment updatedPayment) {

        Payment existingPayment = getPaymentById(paymentId);

        existingPayment.setAmount(updatedPayment.getAmount());
        existingPayment.setPaymentDate(updatedPayment.getPaymentDate());
        existingPayment.setPaymentMethod(updatedPayment.getPaymentMethod());
        existingPayment.setStatus(updatedPayment.getStatus());
        existingPayment.setProposal(updatedPayment.getProposal());

        return paymentRepository.save(existingPayment);
    }

    @Override
    public void deletePayment(String paymentId) {

        Payment existingPayment = getPaymentById(paymentId);

        paymentRepository.delete(existingPayment);
    }

    @Override
    public Payment processPayment(String proposalId, double amount, String paymentMethod) {

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + proposalId));

        if (!"Quote Generated".equals(proposal.getStatus())) {

            throw new IllegalStateException("Payment can only be made for proposals with status 'Quote Generated'.");
        }

        Payment payment = new Payment();

        payment.setProposal(proposal);
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentDate(LocalDate.now());
        payment.setStatus("Completed");

        Payment savedPayment = paymentRepository.save(payment);

        proposal.setStatus("Active");
        proposal.setRemarks("Policy activated. Payment received via " + paymentMethod + ".");

        proposalRepository.save(proposal);

        System.out.println("Payment received for proposal: " + proposalId);
        System.out.println("Policy document sent to user: " + proposal.getUser().getEmail());

        return savedPayment;
    }
}