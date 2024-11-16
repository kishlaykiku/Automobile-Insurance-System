package com.hexaware.ais.service;

import java.util.List;

import com.hexaware.ais.entity.Claim;

/*
 * @Author: Kishlay Kumar
 * Interface: IClaimService
 * Description: This interface defines the contract for claim-related operations
 */
public interface IClaimService {

    /******************************************* Method Signatures *******************************************/

    // Create a new claim
    Claim createClaim(Claim claim);

    // Get a claim by ID
    Claim getClaimById(String claimId);

    // Get all claims
    List<Claim> getAllClaims();

    // Get claims by proposal ID
    List<Claim> getClaimsByProposalId(String proposalId);

    // Update a claim
    Claim updateClaim(String claimId, Claim claim);

    // Delete a claim
    void deleteClaim(String claimId);
}