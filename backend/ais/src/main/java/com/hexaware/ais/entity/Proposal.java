package com.hexaware.ais.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


/*
 * @Author: Kishlay Kumar
 * Class: Proposal Entity
 * Description: This class is used to represent the Proposal entity
 * Map: It is mapped to the `proposal` table in the database
 */
@Entity
@Table(name = "proposal")
public class Proposal {

    /******************************************* Attributes *******************************************/

    @Id
    @Column(name = "proposal_id", nullable = false, unique = true)
    private String proposalId;

    @PastOrPresent(message = "Submission date cannot be in the future")
    @Column(name = "submission_date", nullable = false)
    private LocalDate submissionDate;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "additional_docs")
    private String additionalDocs;

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "officer_id", nullable = false)
    private Officer officer;

    @PrePersist
    protected void generateId() {

        if (this.proposalId == null) {

            this.proposalId = UUID.randomUUID().toString();
        }
    }

    /******************************************* Getters and Setters *******************************************/

    // Getter and Setter for proposalId
    public String getProposalId() {
        return proposalId;
    }
    public void setProposalId(String proposalId) {
        this.proposalId = proposalId;
    }

    // Getter and Setter for submissionDate
    public LocalDate getSubmissionDate() {
        return submissionDate;
    }
    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and Setter for additionalDocs
    public String getAdditionalDocs() {
        return additionalDocs;
    }
    public void setAdditionalDocs(String additionalDocs) {
        this.additionalDocs = additionalDocs;
    }

    // Getter and Setter for remarks
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    // Getter and Setter for user
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    // Getter and Setter for vehicle
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    // Getter and Setter for policy
    public Policy getPolicy() {
        return policy;
    }
    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    // Getter and Setter for officer
    public Officer getOfficer() {
        return officer;
    }
    public void setOfficer(Officer officer) {
        this.officer = officer;
    }
}