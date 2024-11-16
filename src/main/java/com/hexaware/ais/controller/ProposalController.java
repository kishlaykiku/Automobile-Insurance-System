package com.hexaware.ais.controller;

import java.util.List;

import com.hexaware.ais.entity.Proposal;
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
    @PostMapping
    public ResponseEntity<Proposal> createProposal(@RequestBody Proposal proposal) {

        Proposal createdProposal = proposalService.createProposal(proposal);

        return ResponseEntity.ok(createdProposal);
    }

    // Get a proposal by ID
    @GetMapping("/{proposalId}")
    public ResponseEntity<Proposal> getProposalById(@PathVariable String proposalId) {

        Proposal proposal = proposalService.getProposalById(proposalId);

        return ResponseEntity.ok(proposal);
    }

    // Get all proposals
    @GetMapping
    public ResponseEntity<List<Proposal>> getAllProposals() {

        List<Proposal> proposals = proposalService.getAllProposals();

        return ResponseEntity.ok(proposals);
    }

    // Get proposals by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Proposal>> getProposalsByUserId(@PathVariable String userId) {

        List<Proposal> proposals = proposalService.getProposalsByUserId(userId);

        return ResponseEntity.ok(proposals);
    }

    // Update a proposal
    @PutMapping("/{proposalId}")
    public ResponseEntity<Proposal> updateProposal(@PathVariable String proposalId, @RequestBody Proposal proposal) {

        Proposal updatedProposal = proposalService.updateProposal(proposalId, proposal);

        return ResponseEntity.ok(updatedProposal);
    }

    // Delete a proposal
    @DeleteMapping("/{proposalId}")
    public ResponseEntity<String> deleteProposal(@PathVariable String proposalId) {

        proposalService.deleteProposal(proposalId);

        return ResponseEntity.ok("Proposal deleted successfully");
    }
}