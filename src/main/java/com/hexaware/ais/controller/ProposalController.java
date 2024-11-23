package com.hexaware.ais.controller;

import java.util.List;

import com.hexaware.ais.entity.Proposal;
import com.hexaware.ais.dto.ProposalDTO;
import com.hexaware.ais.service.IProposalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // Create a new proposal
    @PostMapping("/create")
    public ResponseEntity<ProposalDTO> createProposal(@RequestBody Proposal proposal) {

        ProposalDTO createdProposal = proposalService.createProposal(proposal);

        return ResponseEntity.ok(createdProposal);
    }

    // Submit a new proposal
    @PostMapping("/submit-proposal")
    public ResponseEntity<ProposalDTO> submitProposal(@RequestBody Proposal proposal) {

        ProposalDTO submittedProposal = proposalService.submitProposal(proposal);

        return ResponseEntity.ok(submittedProposal);
    }

    // Get a proposal by ID
    @GetMapping("/get/{proposalId}")
    public ResponseEntity<ProposalDTO> getProposalById(@PathVariable String proposalId) {

        ProposalDTO proposal = proposalService.getProposalById(proposalId);

        return ResponseEntity.ok(proposal);
    }

    // Get all proposals
    @GetMapping("/getall")
    public ResponseEntity<List<ProposalDTO>> getAllProposals() {

        List<ProposalDTO> proposals = proposalService.getAllProposals();

        return ResponseEntity.ok(proposals);
    }

    // Get proposals by user ID
    @GetMapping("/get/proposal-by-user/{userId}")
    public ResponseEntity<List<ProposalDTO>> getProposalsByUserId(@PathVariable String userId) {

        List<ProposalDTO> proposals = proposalService.getProposalsByUserId(userId);

        return ResponseEntity.ok(proposals);
    }

    // Update a proposal
    @PutMapping("/update/{proposalId}")
    public ResponseEntity<ProposalDTO> updateProposal(@PathVariable String proposalId, @RequestBody Proposal proposal) {

        ProposalDTO updatedProposal = proposalService.updateProposal(proposalId, proposal);

        return ResponseEntity.ok(updatedProposal);
    }

    // Delete a proposal
    @DeleteMapping("/delete/{proposalId}")
    public ResponseEntity<String> deleteProposal(@PathVariable String proposalId) {

        proposalService.deleteProposal(proposalId);

        return ResponseEntity.ok("Proposal deleted successfully");
    }

    // Approve a proposal
    @PutMapping("/approve/{proposalId}")
    public ResponseEntity<ProposalDTO> approveProposal(@PathVariable String proposalId, @RequestParam String remarks) {

        ProposalDTO updatedProposal = proposalService.approveProposal(proposalId, remarks);

        return ResponseEntity.ok(updatedProposal);
    }

    // Reject a proposal
    @PutMapping("/reject/{proposalId}")
    public ResponseEntity<ProposalDTO> rejectProposal(@PathVariable String proposalId, @RequestParam String remarks) {

        ProposalDTO updatedProposal = proposalService.rejectProposal(proposalId, remarks);

        return ResponseEntity.ok(updatedProposal);
    }

    // Request additional details
    @PutMapping("/request-details/{proposalId}")
    public ResponseEntity<ProposalDTO> requestAdditionalDetails(@PathVariable String proposalId, @RequestParam String remarks) {

        ProposalDTO updatedProposal = proposalService.requestAdditionalDetails(proposalId, remarks);

        return ResponseEntity.ok(updatedProposal);
    }

    // Send a quote for the proposal
    @PutMapping("/send-quote/{proposalId}")
    public ResponseEntity<ProposalDTO> sendQuote(@PathVariable String proposalId) {

        ProposalDTO updatedProposal = proposalService.sendQuote(proposalId);

        return ResponseEntity.ok(updatedProposal);
    }
}