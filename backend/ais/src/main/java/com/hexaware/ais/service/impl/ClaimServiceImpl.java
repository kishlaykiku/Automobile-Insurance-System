package com.hexaware.ais.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.hexaware.ais.entity.Claim;
import com.hexaware.ais.entity.Proposal;
import com.hexaware.ais.dto.ClaimDTO;
import com.hexaware.ais.repository.ClaimRepository;
import com.hexaware.ais.repository.ProposalRepository;
import com.hexaware.ais.service.IClaimService;
import com.hexaware.ais.exception.InvalidArgumentException;
import com.hexaware.ais.exception.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * @Author: Kishlay Kumar
 * Class: ClaimServiceImpl
 * Description: This class implements the IClaimService interface for claim-related operations
 */
@Service
public class ClaimServiceImpl implements IClaimService {

    /******************************************* Dependencies *******************************************/

    private static final Logger logger = LoggerFactory.getLogger(ClaimServiceImpl.class);

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private ProposalRepository proposalRepository;

    /******************************************* Methods *******************************************/

    @Override
    public ClaimDTO createClaim(ClaimDTO claimDTO) {

        if (claimDTO == null) {
            logger.error("ClaimDTO is null");
            throw new InvalidArgumentException("Claim data is required.");
        }

        logger.debug("[START] Creating claim for proposal ID: {}", claimDTO.getProposalId());

        Proposal proposal = proposalRepository.findById(claimDTO.getProposalId())
                .orElseThrow(() -> {

                    logger.error("[END] Proposal with ID ({}) not found", claimDTO.getProposalId());
                    return new ResourceNotFoundException("Proposal not found with ID: " + claimDTO.getProposalId());
                }
            );

        Claim claim = claimDTO.toEntity(proposal);
        Claim savedClaim = claimRepository.save(claim);
        
        logger.debug("[END] Claim created successfully with ID: {}", savedClaim.getClaimId());

        return new ClaimDTO(savedClaim);
    }

    @Override
    public ClaimDTO getClaimById(String claimId) {

        if (claimId == null || claimId.isBlank()) {

            logger.error("Claim ID cannot be null or empty");
            throw new InvalidArgumentException("Claim ID is required.");
        }

        logger.debug("[START] Fetching claim with ID: {}", claimId);

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> {

                    logger.error("[END] Claim with ID ({}) not found", claimId);
                    return new ResourceNotFoundException("Claim not found with ID: " + claimId);
                }
            );

        logger.debug("[END] Claim with ID ({}) fetched successfully", claimId);

        return new ClaimDTO(claim);
    }

    @Override
    public List<ClaimDTO> getAllClaims() {

        logger.debug("[START] Fetching all claims");

        List<Claim> claims = claimRepository.findAll();
        List<ClaimDTO> claimDTOs = new ArrayList<>();

        if(claims.isEmpty()) {

            logger.warn("[END] No claims found in the system");
            throw new ResourceNotFoundException("No claims found in the system.");
        }
        else {

            for (Claim claim : claims) {

                claimDTOs.add(new ClaimDTO(claim));
            }

            logger.debug("[END] Fetched all claims successfully");
        }

        return claimDTOs;
    }

    @Override
    public List<ClaimDTO> getClaimsByProposalId(String proposalId) {

        if (proposalId == null || proposalId.isBlank()) {

            logger.error("Proposal ID cannot be null or empty");
            throw new InvalidArgumentException("Proposal ID is required.");
        }

        logger.debug("[START] Fetching claims for proposal ID: {}", proposalId);

        List<Claim> claims = claimRepository.findByProposalProposalId(proposalId);
        List<ClaimDTO> claimDTOs = new ArrayList<>();

        if (claims.isEmpty()) {

            logger.warn("[END] No claims found for proposal ID: {}", proposalId);
            throw new ResourceNotFoundException("No claims found for proposal ID: " + proposalId);
        }
        else {

            for (Claim claim : claims) {

                claimDTOs.add(new ClaimDTO(claim));
            }

            logger.debug("[END] Claims for proposal ID ({}) fetched successfully", proposalId);
        }

        return claimDTOs;
    }

    @Override
    public ClaimDTO updateClaim(String claimId, ClaimDTO claimDTO) {

        logger.debug("[START] Updating claim with ID: {}", claimId);

        if (claimDTO == null) {

            logger.error("[END] ClaimDTO is null");
            throw new InvalidArgumentException("Claim data is required.");
        }

        if (claimId == null || claimId.isBlank()) {

            logger.error("[END] Claim ID cannot be null or empty");
            throw new InvalidArgumentException("Claim ID is required.");
        }

        Claim existingClaim = claimRepository.findById(claimId)
                .orElseThrow(() -> {

                    logger.error("[END] Claim with ID ({}) not found", claimId);
                    return new ResourceNotFoundException("Claim not found with ID: " + claimId);
                }
            );

        Proposal proposal = proposalRepository.findById(claimDTO.getProposalId())
                .orElseThrow(() -> {

                    logger.error("[END] Proposal with ID ({}) not found", claimDTO.getProposalId());
                    return new ResourceNotFoundException("Proposal not found with ID: " + claimDTO.getProposalId());
                }
            );

        existingClaim.setClaimDate(claimDTO.getClaimDate());
        existingClaim.setStatus(claimDTO.getStatus());
        existingClaim.setAmount(claimDTO.getAmount());
        existingClaim.setRemarks(claimDTO.getRemarks());
        existingClaim.setProposal(proposal);

        logger.debug("[END] Claim with ID ({}) updated successfully", claimId);
        
        return new ClaimDTO(claimRepository.save(existingClaim));
    }

    @Override
    public void deleteClaim(String claimId) {

        if (claimId == null || claimId.isBlank()) {

            logger.error("Claim ID cannot be null or empty");
            throw new InvalidArgumentException("Claim ID is required.");
        }

        logger.debug("[START] Deleting claim with ID: {}", claimId);

        Claim existingClaim = claimRepository.findById(claimId)
                .orElseThrow(() -> {

                    logger.error("[END] Claim with ID ({}) not found", claimId);
                    return new ResourceNotFoundException("Claim not found with ID: " + claimId);
                }
            );

        claimRepository.delete(existingClaim);

        logger.debug("[END] Claim with ID ({}) deleted successfully", claimId);
    }
}