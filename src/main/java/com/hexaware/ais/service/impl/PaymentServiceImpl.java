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
import com.hexaware.ais.exception.InvalidArgumentException;
import com.hexaware.ais.exception.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProposalRepository proposalRepository;

    /******************************************* Methods *******************************************/

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {

        if(paymentDTO == null) {

            logger.error("PaymentDTO is null");
            throw new InvalidArgumentException("Payment data is required.");
        }

        logger.debug("[START] Creating payment with amount {} for proposal ID {}", paymentDTO.getAmount(), paymentDTO.getProposalId());

        Payment payment = new Payment();

        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setStatus(paymentDTO.getStatus());

        if (paymentDTO.getProposalId() != null) {

            Proposal proposal = proposalRepository.findById(paymentDTO.getProposalId())
                    .orElseThrow(() -> {

                        logger.error("[END] Proposal with ID ({}) not found", paymentDTO.getProposalId());
                        return new ResourceNotFoundException("Proposal not found with ID: " + paymentDTO.getProposalId());
                    }
                );

            payment.setProposal(proposal);
        }

        Payment savedPayment = paymentRepository.save(payment);

        logger.debug("[END] Payment created successfully with ID: {}", savedPayment.getPaymentId());

        return new PaymentDTO(savedPayment);
    }

    @Override
    public PaymentDTO getPaymentById(String paymentId) {

        if (paymentId == null || paymentId.isBlank()) {

            logger.error("Payment ID cannot be null or empty");
            throw new InvalidArgumentException("Payment ID is required.");
        }

        logger.debug("[START] Fetching payment with ID: {}", paymentId);

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> {

                    logger.error("[END] Payment with ID ({}) not found", paymentId);
                    return new ResourceNotFoundException("Payment not found with ID: " + paymentId);
                }
            );

        logger.debug("[END] Payment with ID ({}) fetched successfully", paymentId);

        return new PaymentDTO(payment);
    }

    @Override
    public List<PaymentDTO> getAllPayments() {

        logger.debug("[START] Fetching all payments");

        List<Payment> payments = paymentRepository.findAll();
        List<PaymentDTO> paymentDTOs = new ArrayList<>();

        if(payments.isEmpty()) {

            logger.warn("[END] No payments found in the system");
            throw new ResourceNotFoundException("No payments found in the system.");
        }
        else {

            for (Payment payment : payments) {

                paymentDTOs.add(new PaymentDTO(payment));
            }

            logger.debug("[END] Fetched all payments successfully");
        }

        return paymentDTOs;
    }

    @Override
    public List<PaymentDTO> getPaymentsByProposalId(String proposalId) {

        if (proposalId == null || proposalId.isBlank()) {

            logger.error("Proposal ID cannot be null or empty");
            throw new InvalidArgumentException("Proposal ID is required.");
        }

        logger.debug("[START] Fetching payments for proposal ID: {}", proposalId);

        List<Payment> payments = paymentRepository.findByProposalProposalId(proposalId);
        List<PaymentDTO> paymentDTOs = new ArrayList<>();

        if (payments.isEmpty()) {

            logger.warn("[END] No payments found for proposal ID: {}", proposalId);
            throw new ResourceNotFoundException("No payments found for proposal ID: " + proposalId);
        }
        else {

            for (Payment payment : payments) {

                paymentDTOs.add(new PaymentDTO(payment));
            }

            logger.debug("[END] Payments for proposal ID ({}) fetched successfully", proposalId);
        }

        return paymentDTOs;
    }

    @Override
    public PaymentDTO updatePayment(String paymentId, PaymentDTO paymentDTO) {

        logger.debug("[START] Updating payment with ID: {}", paymentId);

        if(paymentDTO == null) {

            logger.error("[END] PaymentDTO is null");
            throw new InvalidArgumentException("Payment data is required.");
        }

        if (paymentId == null || paymentId.isBlank()) {

            logger.error("[END] Payment ID cannot be null or empty");
            throw new InvalidArgumentException("Payment ID is required.");
        }

        Payment existingPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> {

                    logger.error("[END] Payment with ID ({}) not found", paymentId);
                    return new ResourceNotFoundException("Payment not found with ID: " + paymentId);
                }
            );

        existingPayment.setAmount(paymentDTO.getAmount());
        existingPayment.setPaymentDate(paymentDTO.getPaymentDate());
        existingPayment.setPaymentMethod(paymentDTO.getPaymentMethod());
        existingPayment.setStatus(paymentDTO.getStatus());

        if (paymentDTO.getProposalId() != null) {

            Proposal proposal = proposalRepository.findById(paymentDTO.getProposalId())
                    .orElseThrow(() -> {

                        logger.error("[END] Proposal with ID ({}) not found", paymentDTO.getProposalId());
                        return new ResourceNotFoundException("Proposal not found with ID: " + paymentDTO.getProposalId());
                    }
                );

            existingPayment.setProposal(proposal);
        }

        Payment updatedPayment = paymentRepository.save(existingPayment);

        logger.debug("[END] Payment with ID ({}) updated successfully", paymentId);

        return new PaymentDTO(updatedPayment);
    }

    @Override
    public void deletePayment(String paymentId) {

        if (paymentId == null || paymentId.isBlank()) {

            logger.error("Payment ID cannot be null or empty");
            throw new InvalidArgumentException("Payment ID is required.");
        }

        logger.debug("[START] Deleting payment with ID: {}", paymentId);

        Payment existingPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> {

                    logger.error("[END] Payment with ID ({}) not found", paymentId);
                    return new ResourceNotFoundException("Payment not found with ID: " + paymentId);
                }
            );

        paymentRepository.delete(existingPayment);

        logger.debug("[END] Payment with ID ({}) deleted successfully", paymentId);
    }

    @Override
    public PaymentDTO processPayment(String proposalId, double amount, String paymentMethod) {

        logger.debug("[START] Processing payment for proposal ID: {}", proposalId);

        if (proposalId == null || proposalId.isBlank()) {

            logger.error("[END] Proposal ID is required");
            throw new InvalidArgumentException("Proposal ID is required.");
        }

        if (amount <= 0) {

            logger.error("[END] Invalid payment amount: {}", amount);
            throw new InvalidArgumentException("Payment amount must be greater than 0.");
        }

        if (paymentMethod == null || paymentMethod.isBlank()) {

            logger.error("[END] Payment method is required");
            throw new InvalidArgumentException("Payment method is required.");
        }

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> {

                    logger.error("[END] Proposal with ID ({}) not found", proposalId);
                    return new ResourceNotFoundException("Proposal not found with ID: " + proposalId);
                }
            );

        if (!"Quote Generated".equals(proposal.getStatus())) {

            logger.error("[END] Payment cannot be processed for proposal ID ({}) as status is not 'Quote Generated'", proposalId);
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

        logger.info("Payment received for proposal: " + proposalId);
        logger.info("Policy document sent to user: " + proposal.getUser().getEmail());

        logger.debug("[END] Payment for proposal ID ({}) processed successfully", proposalId);

        return new PaymentDTO(savedPayment);
    }
}