package com.hexavarsity.ais.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

// Proposal Entity
@Entity
@Table(name = "Proposal")
public class Proposal {

    /************************************ Attributes ************************************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer proposalId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProposalStatus status = ProposalStatus.SUBMITTED;

    @Temporal(TemporalType.TIMESTAMP)
    private Date submittedDate = new Date();

    private Double quote;

    private String policyDocumentUrl;

    @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;

    @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Claim> claims;

    @OneToMany(mappedBy = "proposal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentRequest> documentRequests;

    
    /************************************ Getters and Setters ************************************/

    // Getters and Setters for proposalId
    public Integer getProposalId() {
        return proposalId;
    }
    public void setProposalId(Integer proposalId) {
        this.proposalId = proposalId;
    }

    // Getters and Setters for user
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    // Getters and Setters for policy
    public Policy getPolicy() {
        return policy;
    }
    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    // Getters and Setters for vehicleType
    public VehicleType getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    // Getters and Setters for status
    public ProposalStatus getStatus() {
        return status;
    }
    public void setStatus(ProposalStatus status) {
        this.status = status;
    }

    // Getters and Setters for submittedDate
    public Date getSubmittedDate() {
        return submittedDate;
    }
    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }

    // Getters and Setters for quote
    public Double getQuote() {
        return quote;
    }
    public void setQuote(Double quote) {
        this.quote = quote;
    }

    // Getters and Setters for policyDocumentUrl
    public String getPolicyDocumentUrl() {
        return policyDocumentUrl;
    }
    public void setPolicyDocumentUrl(String policyDocumentUrl) {
        this.policyDocumentUrl = policyDocumentUrl;
    }

    // Getters and Setters for payments
    public List<Payment> getPayments() {
        return payments;
    }
    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    // Getters and Setters for claims
    public List<Claim> getClaims() {
        return claims;
    }
    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }

    // Getters and Setters for documentRequests
    public List<DocumentRequest> getDocumentRequests() {
        return documentRequests;
    }
    public void setDocumentRequests(List<DocumentRequest> documentRequests) {
        this.documentRequests = documentRequests;
    }
}

// VehicleType Enum
enum VehicleType {
    CAR, MOTORCYCLE, CAMPERVAN, TRUCK
}

// ProposalStatus Enum
enum ProposalStatus {
    SUBMITTED, QUOTE_GENERATED, ACTIVE, EXPIRED, REJECTED
}