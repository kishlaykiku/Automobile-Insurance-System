package com.hexaware.ais.service.impl;

import java.util.List;
import java.util.Optional;

import com.hexaware.ais.entity.Claim;
import com.hexaware.ais.repository.ClaimRepository;
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

    /******************************************* Methods *******************************************/

    @Override
    public Claim createClaim(Claim claim) {

        return claimRepository.save(claim);
    }

    @Override
    public Claim getClaimById(String claimId) {

        Optional<Claim> claim = claimRepository.findById(claimId);

        return claim.orElseThrow(() -> new RuntimeException("Claim not found with ID: " + claimId));
    }

    @Override
    public List<Claim> getAllClaims() {

        return claimRepository.findAll();
    }

    @Override
    public List<Claim> getClaimsByProposalId(String proposalId) {

        return claimRepository.findByProposalProposalId(proposalId);
    }

    @Override
    public Claim updateClaim(String claimId, Claim updatedClaim) {

        Claim existingClaim = getClaimById(claimId);

        existingClaim.setClaimDate(updatedClaim.getClaimDate());
        existingClaim.setStatus(updatedClaim.getStatus());
        existingClaim.setAmount(updatedClaim.getAmount());
        existingClaim.setRemarks(updatedClaim.getRemarks());
        existingClaim.setProposal(updatedClaim.getProposal());

        return claimRepository.save(existingClaim);
    }

    @Override
    public void deleteClaim(String claimId) {

        Claim existingClaim = getClaimById(claimId);

        claimRepository.delete(existingClaim);
    }
}