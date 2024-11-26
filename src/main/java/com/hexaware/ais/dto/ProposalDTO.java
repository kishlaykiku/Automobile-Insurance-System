package com.hexaware.ais.dto;

import java.time.LocalDate;

import com.hexaware.ais.entity.Proposal;

/*
 * @Author: Kishlay Kumar
 * Class: ProposalDTO
 * Description: Data Transfer Object for Proposal entity
 */
public class ProposalDTO {

    /******************************************* Attributes *******************************************/

    private String proposalId;
    private LocalDate submissionDate;
    private String status;
    private String additionalDocs;
    private String remarks;
    private String userId;
    private String vehicleId;
    private String policyId;
    private String officerId;

    /******************************************* Constructors *******************************************/

    // Default Constructor
    public ProposalDTO() {

        super();
    }

    // Constructor to map Proposal entity to ProposalDTO
    public ProposalDTO(Proposal proposal) {

        super();

        this.proposalId = proposal.getProposalId();
        this.submissionDate = proposal.getSubmissionDate();
        this.status = proposal.getStatus();
        this.additionalDocs = proposal.getAdditionalDocs();
        this.remarks = proposal.getRemarks();
        this.userId = proposal.getUser() != null ? proposal.getUser().getUserId() : null;
        this.vehicleId = proposal.getVehicle() != null ? proposal.getVehicle().getVehicleId() : null;
        this.policyId = proposal.getPolicy() != null ? proposal.getPolicy().getPolicyId() : null;
        this.officerId = proposal.getOfficer() != null ? proposal.getOfficer().getOfficerId() : null;
    }

    /******************************************* Getters and Setters *******************************************/

    public String getProposalId() {
        return proposalId;
    }
    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }
    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getAdditionalDocs() {
        return additionalDocs;
    }
    public void setAdditionalDocs(String additionalDocs) {
        this.additionalDocs = additionalDocs;
    }

    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPolicyId() {
        return policyId;
    }
    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getOfficerId() {
        return officerId;
    }
    public void setOfficerId(String officerId) {
        this.officerId = officerId;
    }
}