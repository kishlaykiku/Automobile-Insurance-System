package com.hexaware.ais.service;

import java.util.List;

import com.hexaware.ais.dto.ClaimDTO;

/*
 * @Author: Kishlay Kumar
 * Interface: IClaimService
 * Description: This interface defines the contract for claim-related operations
 */
public interface IClaimService {

    /******************************************* Method Signatures *******************************************/

    // Create a new claim
    ClaimDTO createClaim(ClaimDTO claimDto);

    // Get a claim by ID
    ClaimDTO getClaimById(String claimId);

    // Get all claims
    List<ClaimDTO> getAllClaims();

    // Get claims by proposal ID
    List<ClaimDTO> getClaimsByProposalId(String proposalId);

    // Update a claim
    ClaimDTO updateClaim(String claimId, ClaimDTO claim);

    // Delete a claim
    void deleteClaim(String claimId);
}