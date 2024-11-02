package com.hexavarsity.ais.entity;

import jakarta.persistence.*;

// PolicyAddOn Entity
@Entity
@Table(name = "PolicyAddOn")
public class PolicyAddOn {

    /************************************ Attributes ************************************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addonId;

    @ManyToOne
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policy;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Double cost;

    @Lob
    private String eligibility;

    
    /************************************ Getters and Setters ************************************/

    // Getters and Setters for addonId
    public Integer getAddonId() {
        return addonId;
    }
    public void setAddonId(Integer addonId) {
        this.addonId = addonId;
    }

    // Getters and Setters for policy
    public Policy getPolicy() {
        return policy;
    }
    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    // Getters and Setters for name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Getters and Setters for cost
    public Double getCost() {
        return cost;
    }
    public void setCost(Double cost) {
        this.cost = cost;
    }

    // Getters and Setters for eligibility
    public String getEligibility() {
        return eligibility;
    }
    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }
}