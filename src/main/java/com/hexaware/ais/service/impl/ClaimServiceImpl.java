package com.hexaware.ais.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.hexaware.ais.dto.ClaimDTO;
import com.hexaware.ais.entity.Claim;
import com.hexaware.ais.entity.Proposal;
import com.hexaware.ais.repository.ClaimRepository;
import com.hexaware.ais.repository.ProposalRepository;
import com.hexaware.ais.service.IClaimService;

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

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private ProposalRepository proposalRepository;

    /******************************************* Methods *******************************************/

    @Override
    public ClaimDTO createClaim(ClaimDTO claimDTO) {

        Proposal proposal = proposalRepository.findById(claimDTO.getProposalId())
                .orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + claimDTO.getProposalId()));

        Claim claim = claimDTO.toEntity(proposal);

        return new ClaimDTO(claimRepository.save(claim));
    }

    @Override
    public ClaimDTO getClaimById(String claimId) {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found with ID: " + claimId));

        return new ClaimDTO(claim);
    }

    @Override
    public List<ClaimDTO> getAllClaims() {

        List<Claim> claims = claimRepository.findAll();
        List<ClaimDTO> claimDTOs = new ArrayList<>();

        for (Claim claim : claims) {

            claimDTOs.add(new ClaimDTO(claim));
        }

        return claimDTOs;
    }

    @Override
    public List<ClaimDTO> getClaimsByProposalId(String proposalId) {

        List<Claim> claims = claimRepository.findByProposalProposalId(proposalId);
        List<ClaimDTO> claimDTOs = new ArrayList<>();

        for (Claim claim : claims) {

            claimDTOs.add(new ClaimDTO(claim));
        }

        return claimDTOs;
    }

    @Override
    public ClaimDTO updateClaim(String claimId, ClaimDTO claimDTO) {

        Claim existingClaim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found with ID: " + claimId));

        Proposal proposal = proposalRepository.findById(claimDTO.getProposalId())
                .orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + claimDTO.getProposalId()));

        existingClaim.setClaimDate(claimDTO.getClaimDate());
        existingClaim.setStatus(claimDTO.getStatus());
        existingClaim.setAmount(claimDTO.getAmount());
        existingClaim.setRemarks(claimDTO.getRemarks());
        existingClaim.setProposal(proposal);

        return new ClaimDTO(claimRepository.save(existingClaim));
    }

    @Override
    public void deleteClaim(String claimId) {

        Claim existingClaim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found with ID: " + claimId));

        claimRepository.delete(existingClaim);
    }
}