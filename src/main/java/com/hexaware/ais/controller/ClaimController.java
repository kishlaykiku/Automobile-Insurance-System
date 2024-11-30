package com.hexaware.ais.controller;

import java.util.List;

import com.hexaware.ais.dto.ClaimDTO;
import com.hexaware.ais.service.IClaimService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/*
 * @Author: Kishlay Kumar
 * Class: ClaimController
 * Description: This class handles HTTP requests related to claim operations
 */
@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    /******************************************* Dependencies *******************************************/

    @Autowired
    private IClaimService claimService;

    /******************************************* Endpoints *******************************************/

    // Create a new claim (User Only)
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<ClaimDTO> createClaim(@Valid @RequestBody ClaimDTO claimDTO) {

        ClaimDTO createdClaim = claimService.createClaim(claimDTO);

        return ResponseEntity.ok(createdClaim);
    }

    // Get a claim by ID (User and Officer)
    @PreAuthorize("hasAnyRole('USER', 'OFFICER')")
    @GetMapping("/get/{claimId}")
    public ResponseEntity<ClaimDTO> getClaimById(@PathVariable String claimId) {

        ClaimDTO claim = claimService.getClaimById(claimId);

        return ResponseEntity.ok(claim);
    }

    // Get all claims (Officer Only)
    @PreAuthorize("hasRole('OFFICER')")
    @GetMapping("/getall")
    public ResponseEntity<List<ClaimDTO>> getAllClaims() {

        List<ClaimDTO> claims = claimService.getAllClaims();

        return ResponseEntity.ok(claims);
    }

    // Get claims by proposal ID (User Only)
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/claim-by-proposal/{proposalId}")
    public ResponseEntity<List<ClaimDTO>> getClaimsByProposalId(@PathVariable String proposalId) {

        List<ClaimDTO> claims = claimService.getClaimsByProposalId(proposalId);

        return ResponseEntity.ok(claims);
    }

    // Update a claim (User Only)
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/{claimId}")
    public ResponseEntity<ClaimDTO> updateClaim(@PathVariable String claimId, @Valid @RequestBody ClaimDTO claimDTO) {

        ClaimDTO updatedClaim = claimService.updateClaim(claimId, claimDTO);

        return ResponseEntity.ok(updatedClaim);
    }

    // Delete a claim (User Only)
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete/{claimId}")
    public ResponseEntity<String> deleteClaim(@PathVariable String claimId) {

        claimService.deleteClaim(claimId);

        return ResponseEntity.ok("Claim deleted successfully");
    }
}