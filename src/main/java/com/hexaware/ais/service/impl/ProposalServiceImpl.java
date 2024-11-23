package com.hexaware.ais.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.hexaware.ais.entity.Policy;
import com.hexaware.ais.entity.Proposal;
import com.hexaware.ais.repository.ProposalRepository;
import com.hexaware.ais.service.IProposalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * @Author: Kishlay Kumar
 * Class: ProposalServiceImpl
 * Description: This class implements the IProposalService interface for proposal-related operations
 */
@Service
public class ProposalServiceImpl implements IProposalService {

    /******************************************* Dependencies *******************************************/

    @Autowired
    private ProposalRepository proposalRepository;

    /******************************************* Methods *******************************************/

    @Override
    public Proposal createProposal(Proposal proposal) {

        return proposalRepository.save(proposal);
    }

    @Override
    public Proposal submitProposal(Proposal proposal) {

        proposal.setSubmissionDate(LocalDate.now());

        proposal.setStatus("Proposal Submitted");

        return proposalRepository.save(proposal);
    }

    @Override
    public Proposal getProposalById(String proposalId) {

        Optional<Proposal> proposal = proposalRepository.findById(proposalId);

        return proposal.orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + proposalId));
    }

    @Override
    public List<Proposal> getAllProposals() {

        return proposalRepository.findAll();
    }

    @Override
    public List<Proposal> getProposalsByUserId(String userId) {

        return proposalRepository.findByUserUserId(userId);
    }

    @Override
    public Proposal updateProposal(String proposalId, Proposal updatedProposal) {

        Proposal existingProposal = getProposalById(proposalId);

        existingProposal.setSubmissionDate(updatedProposal.getSubmissionDate());
        existingProposal.setStatus(updatedProposal.getStatus());
        existingProposal.setAdditionalDocs(updatedProposal.getAdditionalDocs());
        existingProposal.setUser(updatedProposal.getUser());
        existingProposal.setVehicle(updatedProposal.getVehicle());
        existingProposal.setPolicy(updatedProposal.getPolicy());
        existingProposal.setOfficer(updatedProposal.getOfficer());

        return proposalRepository.save(existingProposal);
    }

    @Override
    public void deleteProposal(String proposalId) {

        Proposal existingProposal = getProposalById(proposalId);

        proposalRepository.delete(existingProposal);
    }

    @Override
    public Proposal approveProposal(String proposalId, String remarks) {

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + proposalId));

        proposal.setStatus("Quote Generated");
        proposal.setRemarks(remarks);

        return proposalRepository.save(proposal);
    }

    @Override
    public Proposal rejectProposal(String proposalId, String remarks) {

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + proposalId));

        proposal.setStatus("Rejected");
        proposal.setRemarks(remarks);

        return proposalRepository.save(proposal);
    }

    @Override
    public Proposal requestAdditionalDetails(String proposalId, String remarks) {

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + proposalId));

        proposal.setStatus("Additional Details Requested");
        proposal.setRemarks(remarks);

        return proposalRepository.save(proposal);
    }

    @Override
    public Proposal sendQuote(String proposalId) {

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + proposalId));

        if (!"Quote Generated".equals(proposal.getStatus())) {

            throw new IllegalStateException("Proposal must have status 'Quote Generated' to send a quote.");
        }

        // Simulation of Premium Calculation
        Policy policy = proposal.getPolicy();
        double  basePremium = policy.getBasePremium();
        double addOnPremium = 0.0;

        if (policy.getAddOns() != null) {

            String[] addOns = policy.getAddOns().split(",");

            addOnPremium = addOns.length * 500;
        }

        double totalPremium = basePremium + addOnPremium;

        // Simulation of sending quote
        System.out.println("Quote sent to user: " + proposal.getUser().getEmail());
        System.out.println("Total Premium: " + totalPremium);

        // Update the proposal remarks with the premium details
        proposal.setRemarks("Quote sent. Total Premium: " + totalPremium);

        return proposalRepository.save(proposal);
    }
}