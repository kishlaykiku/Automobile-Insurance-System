package com.hexaware.ais.dto;

import java.time.LocalDate;

import com.hexaware.ais.entity.Claim;
import com.hexaware.ais.entity.Proposal;

/*
 * @Author: Kishlay Kumar
 * Class: ClaimDTO
 * Description: Data Transfer Object for the Claim entity
 */
public class ClaimDTO {

    /******************************************* Attributes *******************************************/

    private String claimId;
    private LocalDate claimDate;
    private String status;
    private double amount;
    private String remarks;
    private String proposalId;

    /******************************************* Constructors *******************************************/

    public ClaimDTO() {

        super();
    }

    // Constructor to map Claim entity to ClaimDTO
    public ClaimDTO(Claim claim) {

        super();

        this.claimId = claim.getClaimId();
        this.claimDate = claim.getClaimDate();
        this.status = claim.getStatus();
        this.amount = claim.getAmount();
        this.remarks = claim.getRemarks();
        this.proposalId = claim.getProposal() != null ? claim.getProposal().getProposalId() : null;
    }

    /******************************************* Getters and Setters *******************************************/

    public String getClaimId() {
        return claimId;
    }
    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }
    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getProposalId() {
        return proposalId;
    }
    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    /******************************************* Utility Methods *******************************************/

    // Convert DTO to Entity
    public Claim toEntity(Proposal proposal) {

        Claim claim = new Claim();

        claim.setClaimId(this.claimId);
        claim.setClaimDate(this.claimDate);
        claim.setStatus(this.status);
        claim.setAmount(this.amount);
        claim.setRemarks(this.remarks);
        claim.setProposal(proposal);

        return claim;
    }
}