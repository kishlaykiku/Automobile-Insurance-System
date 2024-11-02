package com.hexavarsity.ais.entity;

import jakarta.persistence.*;
import java.util.Date;

// Claim Entity
@Entity
@Table(name = "Claim")
public class Claim {

    /************************************ Attributes ************************************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer claimId;

    @ManyToOne
    @JoinColumn(name = "proposal_id", nullable = false)
    private Proposal proposal;

    @Temporal(TemporalType.TIMESTAMP)
    private Date incidentDate;

    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ClaimStatus status = ClaimStatus.PENDING;

    @Column(nullable = false)
    private Double amountClaimed;

    
    /************************************ Getters and Setters ************************************/

    // Getters and Setters for claimId
    public Integer getClaimId() {
        return claimId;
    }
    public void setClaimId(Integer claimId) {
        this.claimId = claimId;
    }

    // Getters and Setters for proposal
    public Proposal getProposal() {
        return proposal;
    }
    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    // Getters and Setters for incidentDate
    public Date getIncidentDate() {
        return incidentDate;
    }
    public void setIncidentDate(Date incidentDate) {
        this.incidentDate = incidentDate;
    }

    // Getters and Setters for description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // Getters and Setters for status
    public ClaimStatus getStatus() {
        return status;
    }
    public void setStatus(ClaimStatus status) {
        this.status = status;
    }

    // Getters and Setters for amountClaimed
    public Double getAmountClaimed() {
        return amountClaimed;
    }
    public void setAmountClaimed(Double amountClaimed) {
        this.amountClaimed = amountClaimed;
    }
}

// Enum for ClaimStatus
enum ClaimStatus {
    PENDING, APPROVED, REJECTED
}