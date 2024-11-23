package com.hexaware.ais.controller;

import java.util.List;

import com.hexaware.ais.entity.Claim;
import com.hexaware.ais.service.IClaimService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // Create a new claim
    @PostMapping("/create")
    public ResponseEntity<Claim> createClaim(@RequestBody Claim claim) {

        Claim createdClaim = claimService.createClaim(claim);

        return ResponseEntity.ok(createdClaim);
    }

    // Get a claim by ID
    @GetMapping("/get/{claimId}")
    public ResponseEntity<Claim> getClaimById(@PathVariable String claimId) {

        Claim claim = claimService.getClaimById(claimId);

        return ResponseEntity.ok(claim);
    }

    // Get all claims
    @GetMapping("/getall")
    public ResponseEntity<List<Claim>> getAllClaims() {

        List<Claim> claims = claimService.getAllClaims();

        return ResponseEntity.ok(claims);
    }

    // Get claims by proposal ID
    @GetMapping("/get/claim-by-proposal/{proposalId}")
    public ResponseEntity<List<Claim>> getClaimsByProposalId(@PathVariable String proposalId) {

        List<Claim> claims = claimService.getClaimsByProposalId(proposalId);

        return ResponseEntity.ok(claims);
    }

    // Update a claim
    @PutMapping("/update/{claimId}")
    public ResponseEntity<Claim> updateClaim(@PathVariable String claimId, @RequestBody Claim claim) {

        Claim updatedClaim = claimService.updateClaim(claimId, claim);

        return ResponseEntity.ok(updatedClaim);
    }

    // Delete a claim
    @DeleteMapping("/delete/{claimId}")
    public ResponseEntity<String> deleteClaim(@PathVariable String claimId) {

        claimService.deleteClaim(claimId);

        return ResponseEntity.ok("Claim deleted successfully");
    }
}