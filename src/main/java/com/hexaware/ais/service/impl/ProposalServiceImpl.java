package com.hexaware.ais.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import com.hexaware.ais.dto.ProposalDTO;
import com.hexaware.ais.entity.Proposal;
import com.hexaware.ais.repository.ProposalRepository;
import com.hexaware.ais.service.IProposalService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * @Author: Kishlay Kumar
 * Class: ProposalServiceImpl
 * Description: This class implements the IProposalService interface for proposal-related operations
 */
@Service
public class ProposalServiceImpl implements IProposalService {

    /******************************************* Dependencies *******************************************/

    private static final Logger logger = LoggerFactory.getLogger(ProposalServiceImpl.class);

    @Autowired
    private ProposalRepository proposalRepository;

    /******************************************* Methods *******************************************/

    @Override
    public ProposalDTO createProposal(Proposal proposal) {

        logger.debug("[START] Creating proposal for user: {}", proposal.getUser().getUserId());

        Proposal savedProposal = proposalRepository.save(proposal);

        logger.debug("[END] Proposal created with ID: {}", savedProposal.getProposalId());

        return new ProposalDTO(savedProposal);
    }

    @Override
    public ProposalDTO submitProposal(Proposal proposal) {

        logger.debug("[START] Submitting proposal for user: {}", proposal.getUser().getUserId());

        proposal.setSubmissionDate(LocalDate.now());
        proposal.setStatus("Proposal Submitted");

        Proposal submittedProposal = proposalRepository.save(proposal);

        logger.debug("[END] Proposal submitted with ID: {}", submittedProposal.getProposalId());

        return new ProposalDTO(submittedProposal);
    }

    @Override
    public ProposalDTO getProposalById(String proposalId) {

        logger.debug("[START] Fetching proposal with ID: {}", proposalId);

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> {

                    logger.error("[END] Proposal with ID ({}) not found", proposalId);
                    return new RuntimeException("Proposal not found with ID: " + proposalId);
                }
            );

        logger.debug("[END] Proposal with ID ({}) fetched successfully", proposalId);

        return new ProposalDTO(proposal);
    }

    @Override
    public List<ProposalDTO> getAllProposals() {

        List<Proposal> proposals = proposalRepository.findAll();
        List<ProposalDTO> proposalDTOs = new ArrayList<>();

        for (Proposal proposal : proposals) {

            proposalDTOs.add(new ProposalDTO(proposal));
        }

        return proposalDTOs;
    }

    @Override
    public List<ProposalDTO> getProposalsByUserId(String userId) {

        logger.debug("[START] Fetching all proposals");

        List<Proposal> proposals = proposalRepository.findByUserUserId(userId);
        List<ProposalDTO> proposalDTOs = new ArrayList<>();

        if(proposals.isEmpty()) {

            logger.warn("[END] No proposals found for user ID: {}", userId);
        }
        else {

            for (Proposal proposal : proposals) {

                proposalDTOs.add(new ProposalDTO(proposal));
            }

            logger.debug("[END] Fetched all proposals successfully");
        }

        return proposalDTOs;
    }

    @Override
    public ProposalDTO updateProposal(String proposalId, Proposal updatedProposal) {

        logger.debug("[START] Updating proposal with ID: {}", proposalId);

        Proposal existingProposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> {

                    logger.error("[END] Proposal with ID ({}) not found", proposalId);
                    return new RuntimeException("Proposal not found with ID: " + proposalId);
                }
            );

        existingProposal.setSubmissionDate(updatedProposal.getSubmissionDate());
        existingProposal.setStatus(updatedProposal.getStatus());
        existingProposal.setAdditionalDocs(updatedProposal.getAdditionalDocs());
        existingProposal.setRemarks(updatedProposal.getRemarks());
        existingProposal.setUser(updatedProposal.getUser());
        existingProposal.setVehicle(updatedProposal.getVehicle());
        existingProposal.setPolicy(updatedProposal.getPolicy());
        existingProposal.setOfficer(updatedProposal.getOfficer());

        Proposal savedProposal = proposalRepository.save(existingProposal);

        logger.debug("[END] Proposal with ID ({}) updated successfully", proposalId);

        return new ProposalDTO(savedProposal);
    }

    @Override
    public void deleteProposal(String proposalId) {

        logger.debug("[START] Deleting proposal with ID: {}", proposalId);

        Proposal existingProposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> {

                    logger.error("[END] Proposal with ID ({}) not found", proposalId);
                    return new RuntimeException("Proposal not found with ID: " + proposalId);
                }
            );

        proposalRepository.delete(existingProposal);

        logger.debug("[END] Proposal with ID ({}) deleted successfully", proposalId);
    }

    @Override
    public ProposalDTO approveProposal(String proposalId, String remarks) {

        logger.debug("[START] Approving proposal with ID: {}", proposalId);

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> {

                    logger.error("[END] Proposal with ID ({}) not found", proposalId);
                    return new RuntimeException("Proposal not found with ID: " + proposalId);
                }
            );

        proposal.setStatus("Quote Generated");
        proposal.setRemarks(remarks);

        Proposal savedProposal = proposalRepository.save(proposal);

        logger.debug("[END] Proposal with ID ({}) approved successfully", proposalId);

        return new ProposalDTO(savedProposal);
    }

    @Override
    public ProposalDTO rejectProposal(String proposalId, String remarks) {

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> {

                    logger.error("[END] Proposal with ID ({}) not found", proposalId);
                    return new RuntimeException("Proposal not found with ID: " + proposalId);
                }
            );

        proposal.setStatus("Rejected");
        proposal.setRemarks(remarks);

        Proposal savedProposal = proposalRepository.save(proposal);

        logger.debug("[END] Proposal with ID ({}) rejected successfully", proposalId);

        return new ProposalDTO(savedProposal);
    }

    @Override
    public ProposalDTO requestAdditionalDetails(String proposalId, String remarks) {

        logger.debug("[START] Requesting additional details for proposal ID: {}", proposalId);

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> {

                    logger.error("[END] Proposal with ID ({}) not found", proposalId);
                    return new RuntimeException("Proposal not found with ID: " + proposalId);
                }
            );

        proposal.setStatus("Additional Details Requested");
        proposal.setRemarks(remarks);

        Proposal savedProposal = proposalRepository.save(proposal);

        logger.debug("[END] Additional details requested for proposal ID: {}", proposalId);

        return new ProposalDTO(savedProposal);
    }

    @Override
    public ProposalDTO sendQuote(String proposalId) {

        logger.debug("[START] Sending quote for proposal ID: {}", proposalId);

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> {

                    logger.error("[END] Proposal with ID ({}) not found", proposalId);
                    return new RuntimeException("Proposal not found with ID: " + proposalId);
                }
            );

        if (!"Quote Generated".equals(proposal.getStatus())) {

            logger.error("[END] Proposal with ID ({}) must have status 'Quote Generated' to send a quote", proposalId);
            throw new IllegalStateException("Proposal must have status 'Quote Generated' to send a quote.");
        }

        double basePremium = proposal.getPolicy().getBasePremium();
        double addOnPremium = 0.0;

        if (proposal.getPolicy().getAddOns() != null) {

            String[] addOns = proposal.getPolicy().getAddOns().split(",");
            addOnPremium = addOns.length * 500;
        }

        double totalPremium = basePremium + addOnPremium;

        logger.info("Quote sent to user: {}", proposal.getUser().getEmail());
        logger.info("Total Premium: {}", totalPremium);

        proposal.setRemarks("Quote sent. Total Premium: " + totalPremium);

        Proposal savedProposal = proposalRepository.save(proposal);

        logger.debug("[END] Quote sent for proposal ID: {}", proposalId);

        return new ProposalDTO(savedProposal);
    }
}