package com.hexaware.ais.service;

import java.util.List;

import com.hexaware.ais.entity.Proposal;


/*
 * @Author: Kishlay Kumar
 * Interface: IProposalService
 * Description: This interface defines the contract for proposal-related operations
 */
public interface IProposalService {

    /******************************************* Method Signatures *******************************************/

    // Create a new proposal
    Proposal createProposal(Proposal proposal);

    // Get a proposal by ID
    Proposal getProposalById(String proposalId);

    // Get all proposals
    List<Proposal> getAllProposals();

    // Get proposals by user ID
    List<Proposal> getProposalsByUserId(String userId);

    // Update a proposal
    Proposal updateProposal(String proposalId, Proposal proposal);

    // Delete a proposal
    void deleteProposal(String proposalId);
}