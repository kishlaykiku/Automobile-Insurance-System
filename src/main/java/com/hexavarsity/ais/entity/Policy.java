package com.hexavarsity.ais.entity;

import jakarta.persistence.*;
import java.util.List;

// Policy Entity
@Entity
@Table(name = "Policy")
public class Policy {

    /************************************ Attributes ************************************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer policyId;

    @Column(nullable = false, length = 100)
    private String name;

    @Lob
    private String description;

    @Column(nullable = false)
    private Double basePremium;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PolicyStatus status = PolicyStatus.ACTIVE;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proposal> proposals;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PolicyAddOn> addOns;

    
    /************************************ Getters and Setters ************************************/

    // Getters and Setters for policyId
    public Integer getPolicyId() {
        return policyId;
    }
    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    // Getters and Setters for name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Getters and Setters for description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // Getters and Setters for basePremium
    public Double getBasePremium() {
        return basePremium;
    }
    public void setBasePremium(Double basePremium) {
        this.basePremium = basePremium;
    }

    // Getters and Setters for status
    public PolicyStatus getStatus() {
        return status;
    }
    public void setStatus(PolicyStatus status) {
        this.status = status;
    }

    // Getters and Setters for proposals
    public List<Proposal> getProposals() {
        return proposals;
    }
    public void setProposals(List<Proposal> proposals) {
        this.proposals = proposals;
    }

    // Getters and Setters for addOns
    public List<PolicyAddOn> getAddOns() {
        return addOns;
    }
    public void setAddOns(List<PolicyAddOn> addOns) {
        this.addOns = addOns;
    }
}

// Enum for PolicyStatus
enum PolicyStatus {
    ACTIVE, INACTIVE
}