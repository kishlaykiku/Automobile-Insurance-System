package com.hexaware.ais.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import com.hexaware.ais.dto.ProposalDTO;
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
    public ProposalDTO createProposal(Proposal proposal) {

        Proposal savedProposal = proposalRepository.save(proposal);

        return new ProposalDTO(savedProposal);
    }

    @Override
    public ProposalDTO submitProposal(Proposal proposal) {

        proposal.setSubmissionDate(LocalDate.now());
        proposal.setStatus("Proposal Submitted");

        Proposal submittedProposal = proposalRepository.save(proposal);

        return new ProposalDTO(submittedProposal);
    }

    @Override
    public ProposalDTO getProposalById(String proposalId) {

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + proposalId));

        return new ProposalDTO(proposal);
    }

    @Override
    public List<ProposalDTO> getAllProposals() {

        List<Proposal> proposals = proposalRepository.findAll();
        List<ProposalDTO> proposalDTOs = new ArrayList<>();

        for (Proposal proposal : proposals) {

            proposalDTOs.add(new ProposalDTO(proposal));
        }

        return proposalDTOs;
    }

    @Override
    public List<ProposalDTO> getProposalsByUserId(String userId) {

        List<Proposal> proposals = proposalRepository.findByUserUserId(userId);
        List<ProposalDTO> proposalDTOs = new ArrayList<>();

        for (Proposal proposal : proposals) {

            proposalDTOs.add(new ProposalDTO(proposal));
        }

        return proposalDTOs;
    }

    @Override
    public ProposalDTO updateProposal(String proposalId, Proposal updatedProposal) {

        Proposal existingProposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + proposalId));

        existingProposal.setSubmissionDate(updatedProposal.getSubmissionDate());
        existingProposal.setStatus(updatedProposal.getStatus());
        existingProposal.setAdditionalDocs(updatedProposal.getAdditionalDocs());
        existingProposal.setRemarks(updatedProposal.getRemarks());
        existingProposal.setUser(updatedProposal.getUser());
        existingProposal.setVehicle(updatedProposal.getVehicle());
        existingProposal.setPolicy(updatedProposal.getPolicy());
        existingProposal.setOfficer(updatedProposal.getOfficer());

        Proposal savedProposal = proposalRepository.save(existingProposal);

        return new ProposalDTO(savedProposal);
    }

    @Override
    public void deleteProposal(String proposalId) {

        Proposal existingProposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + proposalId));

        proposalRepository.delete(existingProposal);
    }

    @Override
    public ProposalDTO approveProposal(String proposalId, String remarks) {

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + proposalId));

        proposal.setStatus("Quote Generated");
        proposal.setRemarks(remarks);

        Proposal savedProposal = proposalRepository.save(proposal);

        return new ProposalDTO(savedProposal);
    }

    @Override
    public ProposalDTO rejectProposal(String proposalId, String remarks) {

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + proposalId));

        proposal.setStatus("Rejected");
        proposal.setRemarks(remarks);

        Proposal savedProposal = proposalRepository.save(proposal);

        return new ProposalDTO(savedProposal);
    }

    @Override
    public ProposalDTO requestAdditionalDetails(String proposalId, String remarks) {

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + proposalId));

        proposal.setStatus("Additional Details Requested");
        proposal.setRemarks(remarks);

        Proposal savedProposal = proposalRepository.save(proposal);

        return new ProposalDTO(savedProposal);
    }

    @Override
    public ProposalDTO sendQuote(String proposalId) {

        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new RuntimeException("Proposal not found with ID: " + proposalId));

        if (!"Quote Generated".equals(proposal.getStatus())) {

            throw new IllegalStateException("Proposal must have status 'Quote Generated' to send a quote.");
        }

        double basePremium = proposal.getPolicy().getBasePremium();
        double addOnPremium = 0.0;

        if (proposal.getPolicy().getAddOns() != null) {

            String[] addOns = proposal.getPolicy().getAddOns().split(",");
            addOnPremium = addOns.length * 500;
        }

        double totalPremium = basePremium + addOnPremium;

        System.out.println("Quote sent to user: " + proposal.getUser().getEmail());
        System.out.println("Total Premium: " + totalPremium);

        proposal.setRemarks("Quote sent. Total Premium: " + totalPremium);

        Proposal savedProposal = proposalRepository.save(proposal);

        return new ProposalDTO(savedProposal);
    }
}