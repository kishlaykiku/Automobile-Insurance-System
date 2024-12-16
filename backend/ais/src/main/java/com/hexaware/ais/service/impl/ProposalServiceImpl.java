package com.hexaware.ais.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import com.hexaware.ais.entity.Officer;
import com.hexaware.ais.entity.Proposal;
import com.hexaware.ais.entity.User;
import com.hexaware.ais.entity.Vehicle;
import com.hexaware.ais.dto.PolicyDTO;
import com.hexaware.ais.dto.ProposalDTO;
import com.hexaware.ais.dto.UserDTO;
import com.hexaware.ais.dto.VehicleDTO;
import com.hexaware.ais.repository.OfficerRepository;
import com.hexaware.ais.repository.ProposalRepository;
import com.hexaware.ais.repository.UserRepository;
import com.hexaware.ais.repository.VehicleRepository;
import com.hexaware.ais.service.IProposalService;
import com.hexaware.ais.exception.InvalidArgumentException;
import com.hexaware.ais.exception.ResourceNotFoundException;

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

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfficerRepository officerRepository;

    /******************************************* Methods *******************************************/

    @Override
    public ProposalDTO submitProposal(Proposal proposal) {

        if(proposal == null) {

            logger.error("Proposal is null");
            throw new InvalidArgumentException("Proposal data is required.");
        }

        if (proposal.getUser() == null || proposal.getUser().getUserId() == null) {

            logger.error("User information is missing in the proposal");
            throw new InvalidArgumentException("User information is required.");
        }

        if (proposal.getVehicle() == null) {

            throw new InvalidArgumentException("Vehicle information is required.");
        }

        if (proposal.getOfficer() == null || proposal.getOfficer().getOfficerId() == null) {

            throw new InvalidArgumentException("Officer information is required.");
        }

        logger.debug("[START] Submitting proposal for user: {}", proposal.getUser().getUserId());

        User user = userRepository.findById(proposal.getUser().getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + proposal.getUser().getUserId()));

        Officer officer = officerRepository.findById(proposal.getOfficer().getOfficerId())
            .orElseThrow(() -> new ResourceNotFoundException("Officer not found with ID: " + proposal.getOfficer().getOfficerId()));

        proposal.setOfficer(officer);

        Vehicle vehicle = proposal.getVehicle();
        vehicle.setUser(user);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        proposal.setVehicle(savedVehicle);

        proposal.setSubmissionDate(LocalDate.now());
        proposal.setStatus("Proposal Submitted");

        Proposal submittedProposal = proposalRepository.save(proposal);

        logger.debug("[END] Proposal submitted with ID: {}", submittedProposal.getProposalId());

        return new ProposalDTO(submittedProposal);
    }

    @Override
    public ProposalDTO getProposalById(String proposalId) {

        if (proposalId == null || proposalId.isBlank()) {

            logger.error("Proposal ID cannot be null or empty");
            throw new InvalidArgumentException("Proposal ID is required.");
        }

        logger.debug("[START] Fetching proposal with ID: {}", proposalId);

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> {

                    logger.error("[END] Proposal with ID ({}) not found", proposalId);
                    return new ResourceNotFoundException("Proposal not found with ID: " + proposalId);
                }
            );

        ProposalDTO proposalDTO = new ProposalDTO(proposal);

        // Map related entities to DTOs
        if (proposal.getPolicy() != null) {

            proposalDTO.setPolicy(new PolicyDTO(proposal.getPolicy()));
        }
        if (proposal.getVehicle() != null) {

            proposalDTO.setVehicle(new VehicleDTO(proposal.getVehicle()));
        }
        if (proposal.getUser() != null) {

            proposalDTO.setUser(new UserDTO(proposal.getUser()));
        }
        if (proposal.getOfficer() != null) {

            proposalDTO.setOfficerName(proposal.getOfficer().getName());
        }

        logger.debug("[END] Proposal with ID ({}) fetched successfully", proposalId);

            return proposalDTO;
    }

    @Override
    public List<ProposalDTO> getAllProposals() {

        logger.debug("[START] Fetching all proposals");

        List<Proposal> proposals = proposalRepository.findAll();
        List<ProposalDTO> proposalDTOs = new ArrayList<>();

        if(proposals.isEmpty()) {

            logger.warn("No proposals found in the system");
            throw new ResourceNotFoundException("No proposals found in the system.");
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
    public List<ProposalDTO> getProposalsByUserId(String userId) {

        if (userId == null || userId.isBlank()) {

            logger.error("User ID cannot be null or empty");
            throw new InvalidArgumentException("User ID is required.");
        }

        logger.debug("[START] Fetching all proposals for user ID: {}", userId);

        List<Proposal> proposals = proposalRepository.findByUserUserId(userId);
        List<ProposalDTO> proposalDTOs = new ArrayList<>();

        if(proposals.isEmpty()) {

            logger.warn("[END] No proposals found for user ID: {}", userId);
            throw new ResourceNotFoundException("No proposals found for user ID: " + userId);
        }
        else {

            for (Proposal proposal : proposals) {
                ProposalDTO proposalDTO = new ProposalDTO(proposal);

                // Map related entities to DTOs
                if (proposal.getPolicy() != null) {

                    proposalDTO.setPolicy(new PolicyDTO(proposal.getPolicy()));
                }
                if (proposal.getVehicle() != null) {

                    proposalDTO.setVehicle(new VehicleDTO(proposal.getVehicle()));
                }
                if (proposal.getUser() != null) {

                    proposalDTO.setUser(new UserDTO(proposal.getUser()));
                }
                if (proposal.getOfficer() != null) {

                    proposalDTO.setOfficerName(proposal.getOfficer().getName());
                }

                proposalDTOs.add(proposalDTO);
            }

            logger.debug("[END] Fetched all proposals successfully for user ID: {}", userId);
        }

        return proposalDTOs;
    }

    @Override
    public ProposalDTO updateProposal(String proposalId, Proposal updatedProposal) {

        logger.debug("[START] Updating proposal with ID: {}", proposalId);

        if(updatedProposal == null) {

            logger.error("[END] Proposal is null");
            throw new InvalidArgumentException("Proposal data is required.");
        }

        if (proposalId == null || proposalId.isBlank()) {

            logger.error("[END] Proposal ID cannot be null or empty");
            throw new InvalidArgumentException("Proposal ID is required.");
        }

        Proposal existingProposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> {

                    logger.error("[END] Proposal with ID ({}) not found", proposalId);
                    return new ResourceNotFoundException("Proposal not found with ID: " + proposalId);
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

        if (proposalId == null || proposalId.isBlank()) {

            logger.error("Proposal ID cannot be null or empty");
            throw new InvalidArgumentException("Proposal ID is required.");
        }

        logger.debug("[START] Deleting proposal with ID: {}", proposalId);

        Proposal existingProposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> {

                    logger.error("[END] Proposal with ID ({}) not found", proposalId);
                    return new ResourceNotFoundException("Proposal not found with ID: " + proposalId);
                }
            );

        proposalRepository.delete(existingProposal);

        logger.debug("[END] Proposal with ID ({}) deleted successfully", proposalId);
    }

    @Override
    public ProposalDTO approveProposal(String proposalId, String remarks) {

        if (proposalId == null || proposalId.isBlank()) {

            logger.error("Proposal ID cannot be null or empty");
            throw new InvalidArgumentException("Proposal ID is required.");
        }

        logger.debug("[START] Approving proposal with ID: {}", proposalId);

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> {

                    logger.error("[END] Proposal with ID ({}) not found", proposalId);
                    return new ResourceNotFoundException("Proposal not found with ID: " + proposalId);
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

        if (proposalId == null || proposalId.isBlank()) {

            logger.error("Proposal ID cannot be null or empty");
            throw new InvalidArgumentException("Proposal ID is required.");
        }

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> {

                    logger.error("[END] Proposal with ID ({}) not found", proposalId);
                    return new ResourceNotFoundException("Proposal not found with ID: " + proposalId);
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

        if (proposalId == null || proposalId.isBlank()) {

            logger.error("Proposal ID cannot be null or empty");
            throw new InvalidArgumentException("Proposal ID is required.");
        }

        logger.debug("[START] Requesting additional details for proposal ID: {}", proposalId);

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> {

                    logger.error("[END] Proposal with ID ({}) not found", proposalId);
                    return new ResourceNotFoundException("Proposal not found with ID: " + proposalId);
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

        if (proposalId == null || proposalId.isBlank()) {

            logger.error("Proposal ID cannot be null or empty");
            throw new InvalidArgumentException("Proposal ID is required.");
        }

        logger.debug("[START] Sending quote for proposal ID: {}", proposalId);

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> {

                    logger.error("[END] Proposal with ID ({}) not found", proposalId);
                    return new ResourceNotFoundException("Proposal not found with ID: " + proposalId);
                }
            );

        if (!"Quote Generated".equals(proposal.getStatus())) {

            logger.error("[END] Proposal with ID ({}) must have status 'Quote Generated' to send a quote", proposalId);
            throw new IllegalStateException("Proposal must have status 'Quote Generated' to send a quote.");
        }

        if (proposal.getPolicy() == null) {

            logger.error("[END] Proposal with ID ({}) does not have a valid policy", proposalId);
            throw new InvalidArgumentException("Proposal does not have a valid policy.");
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