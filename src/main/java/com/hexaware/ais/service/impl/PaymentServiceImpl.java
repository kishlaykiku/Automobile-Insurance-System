package com.hexaware.ais.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import com.hexaware.ais.entity.Payment;
import com.hexaware.ais.entity.Proposal;
import com.hexaware.ais.dto.PaymentDTO;
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
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {

        Payment payment = new Payment();

        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setStatus(paymentDTO.getStatus());

        if (paymentDTO.getProposalId() != null) {

            Proposal proposal = proposalRepository.findById(paymentDTO.getProposalId())
                    .orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + paymentDTO.getProposalId()));

            payment.setProposal(proposal);
        }

        Payment savedPayment = paymentRepository.save(payment);

        return new PaymentDTO(savedPayment);
    }

    @Override
    public PaymentDTO getPaymentById(String paymentId) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + paymentId));

        return new PaymentDTO(payment);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {

        List<Payment> payments = paymentRepository.findAll();
        List<PaymentDTO> paymentDTOs = new ArrayList<>();

        for (Payment payment : payments) {

            paymentDTOs.add(new PaymentDTO(payment));
        }

        return paymentDTOs;
    }

    @Override
    public List<PaymentDTO> getPaymentsByProposalId(String proposalId) {

        List<Payment> payments = paymentRepository.findByProposalProposalId(proposalId);
        List<PaymentDTO> paymentDTOs = new ArrayList<>();

        for (Payment payment : payments) {

            paymentDTOs.add(new PaymentDTO(payment));
        }

        return paymentDTOs;
    }

    @Override
    public PaymentDTO updatePayment(String paymentId, PaymentDTO paymentDTO) {

        Payment existingPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + paymentId));

        existingPayment.setAmount(paymentDTO.getAmount());
        existingPayment.setPaymentDate(paymentDTO.getPaymentDate());
        existingPayment.setPaymentMethod(paymentDTO.getPaymentMethod());
        existingPayment.setStatus(paymentDTO.getStatus());

        if (paymentDTO.getProposalId() != null) {

            Proposal proposal = proposalRepository.findById(paymentDTO.getProposalId())
                    .orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + paymentDTO.getProposalId()));

            existingPayment.setProposal(proposal);
        }

        Payment updatedPayment = paymentRepository.save(existingPayment);

        return new PaymentDTO(updatedPayment);
    }

    @Override
    public void deletePayment(String paymentId) {

        Payment existingPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + paymentId));

        paymentRepository.delete(existingPayment);
    }

    @Override
    public PaymentDTO processPayment(String proposalId, double amount, String paymentMethod) {

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

        return new PaymentDTO(savedPayment);
    }
}