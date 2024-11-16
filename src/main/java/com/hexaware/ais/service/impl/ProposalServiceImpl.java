package com.hexaware.ais.service.impl;

import java.util.List;
import java.util.Optional;

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
}