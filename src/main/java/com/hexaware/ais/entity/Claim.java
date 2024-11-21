package com.hexaware.ais.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


/*
 * @Author: Kishlay Kumar
 * Class: Claim Entity
 * Description: This class is used to represent the Claim entity
 * Map: It is mapped to the `claim` table in the database
 */
@Entity
@Table(name = "claim")
public class Claim {

    /******************************************* Attributes *******************************************/

    @Id
    @Column(name = "claim_id", nullable = false, unique = true)
    private String claimId;

    @NotNull(message = "Claim date is required")
    @PastOrPresent(message = "Claim date cannot be in the future")
    @Column(name = "claim_date", nullable = false)
    private LocalDate claimDate;

    @NotBlank(message = "Status is required")
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull(message = "Claim amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Claim amount must be greater than 0")
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proposal_id", nullable = false)
    private Proposal proposal;

    @PrePersist
    protected void generateId() {

        if (this.claimId == null) {

            this.claimId = UUID.randomUUID().toString();
        }
    }

    /******************************************* Getters and Setters *******************************************/

    // Getter and Setter for claimId
    public String getClaimId() {
        return claimId;
    }
    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    // Getter and Setter for claimDate
    public LocalDate getClaimDate() {
        return claimDate;
    }
    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and Setter for amount
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    // Getter and Setter for remarks
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    // Getter and Setter for proposal
    public Proposal getProposal() {
        return proposal;
    }
    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }
}