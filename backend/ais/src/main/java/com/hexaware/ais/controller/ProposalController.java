package com.hexaware.ais.controller;

import java.util.List;

import com.hexaware.ais.entity.Proposal;
import com.hexaware.ais.dto.ProposalDTO;
import com.hexaware.ais.service.IProposalService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/*
 * @Author: Kishlay Kumar
 * Class: ProposalController
 * Description: This class handles HTTP requests related to proposal operations
 */
@RestController
@RequestMapping("/api/proposals")
public class ProposalController {

    /******************************************* Dependencies *******************************************/

    @Autowired
    private IProposalService proposalService;

    /******************************************* Endpoints *******************************************/

    // Submit a new proposal (User Only)
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/submit-proposal")
    public ResponseEntity<ProposalDTO> submitProposal(@Valid @RequestBody Proposal proposal) {

        ProposalDTO submittedProposal = proposalService.submitProposal(proposal);

        return ResponseEntity.ok(submittedProposal);
    }

    // Get a proposal by ID (User and Officer)
    @PreAuthorize("hasAnyRole('USER', 'OFFICER')")
    @GetMapping("/get/{proposalId}")
    public ResponseEntity<ProposalDTO> getProposalById(@PathVariable String proposalId) {

        ProposalDTO proposal = proposalService.getProposalById(proposalId);

        return ResponseEntity.ok(proposal);
    }

    // Get all proposals (Officer Only)
    @PreAuthorize("hasRole('OFFICER')")
    @GetMapping("/getall")
    public ResponseEntity<List<ProposalDTO>> getAllProposals() {

        List<ProposalDTO> proposals = proposalService.getAllProposals();

        return ResponseEntity.ok(proposals);
    }

    // Get proposals by user ID (User Only)
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/proposal-by-user/{userId}")
    public ResponseEntity<List<ProposalDTO>> getProposalsByUserId(@PathVariable String userId) {

        List<ProposalDTO> proposals = proposalService.getProposalsByUserId(userId);

        return ResponseEntity.ok(proposals);
    }

    // Update a proposal (User Only)
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/{proposalId}")
    public ResponseEntity<ProposalDTO> updateProposal(@PathVariable String proposalId, @Valid @RequestBody Proposal proposal) {

        ProposalDTO updatedProposal = proposalService.updateProposal(proposalId, proposal);

        return ResponseEntity.ok(updatedProposal);
    }

    // Delete a proposal (User-only)
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/delete/{proposalId}")
    public ResponseEntity<String> deleteProposal(@PathVariable String proposalId) {

        proposalService.deleteProposal(proposalId);

        return ResponseEntity.ok("Proposal deleted successfully");
    }

    // Approve a proposal (Officer-only)
    @PreAuthorize("hasRole('OFFICER')")
    @PutMapping("/approve/{proposalId}")
    public ResponseEntity<ProposalDTO> approveProposal(@PathVariable String proposalId, @RequestParam String remarks) {

        ProposalDTO updatedProposal = proposalService.approveProposal(proposalId, remarks);

        return ResponseEntity.ok(updatedProposal);
    }

    // Reject a proposal (Officer-only)
    @PreAuthorize("hasRole('OFFICER')")
    @PutMapping("/reject/{proposalId}")
    public ResponseEntity<ProposalDTO> rejectProposal(@PathVariable String proposalId, @RequestParam String remarks) {

        ProposalDTO updatedProposal = proposalService.rejectProposal(proposalId, remarks);

        return ResponseEntity.ok(updatedProposal);
    }

    // Request additional details (Officer-only)
    @PreAuthorize("hasRole('OFFICER')")
    @PutMapping("/request-details/{proposalId}")
    public ResponseEntity<ProposalDTO> requestAdditionalDetails(@PathVariable String proposalId, @RequestParam String remarks) {

        ProposalDTO updatedProposal = proposalService.requestAdditionalDetails(proposalId, remarks);

        return ResponseEntity.ok(updatedProposal);
    }

    // Send a quote for the proposal (Officer-only)
    @PreAuthorize("hasRole('OFFICER')")
    @PutMapping("/send-quote/{proposalId}")
    public ResponseEntity<ProposalDTO> sendQuote(@PathVariable String proposalId) {

        ProposalDTO updatedProposal = proposalService.sendQuote(proposalId);

        return ResponseEntity.ok(updatedProposal);
    }
}