package com.hexaware.ais.service;

import java.util.List;

import com.hexaware.ais.entity.Proposal;
import com.hexaware.ais.dto.ProposalDTO;;


/*
 * @Author: Kishlay Kumar
 * Interface: IProposalService
 * Description: This interface defines the contract for proposal-related operations
 */
public interface IProposalService {

    /******************************************* Method Signatures *******************************************/

    // Create a new proposal
    ProposalDTO createProposal(Proposal proposal);

    // Submit a proposal
    ProposalDTO submitProposal(Proposal proposal);

    // Get a proposal by ID
    ProposalDTO getProposalById(String proposalId);

    // Get all proposals
    List<ProposalDTO> getAllProposals();

    // Get proposals by user ID
    List<ProposalDTO> getProposalsByUserId(String userId);

    // Update a proposal
    ProposalDTO updateProposal(String proposalId, Proposal proposal);

    // Delete a proposal
    void deleteProposal(String proposalId);

    // Approve a proposal
    ProposalDTO approveProposal(String proposalId, String remarks);

    // Reject a proposal
    ProposalDTO rejectProposal(String proposalId, String remarks);

    // Request additional details
    ProposalDTO requestAdditionalDetails(String proposalId, String remarks);

    // Generate and "send" a quote for the proposal
    ProposalDTO sendQuote(String proposalId);
}