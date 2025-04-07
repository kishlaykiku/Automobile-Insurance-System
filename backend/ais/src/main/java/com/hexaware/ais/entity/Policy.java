package com.hexaware.ais.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


/*
 * @Author: Kishlay Kumar
 * Class: Policy Entity
 * Description: This class is used to represent the Policy entity
 * Map: It is mapped to the `policy` table in the database
 */
@Entity
@Table(name = "policy")
public class Policy {

    /******************************************* Attributes *******************************************/

    @Id
    @Column(name = "policy_id", nullable = false, unique = true)
    private String policyId;

    @NotBlank(message = "Policy number is required")
    @Column(name = "policy_no", nullable = false, unique = true)
    private String policyNo;

    @NotBlank(message = "Policy name is required")
    @Column(name = "policy_name", nullable = false)
    private String policyName;

    @NotBlank(message = "Policy type is required")
    @Column(name = "type", nullable = false)
    private String type;

    @NotNull(message = "Base premium is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Base premium must be greater than 0")
    @Column(name = "base_premium", nullable = false)
    private double basePremium;

    @Column(name = "features")
    private String features;

    @Column(name = "add_ons")
    private String addOns;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or in the future")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "renewal_date")
    private LocalDate renewalDate;

    @NotBlank(message = "Status is required")
    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "reminder_sent", nullable = false)
    private boolean reminderSent = false;

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @PrePersist
    protected void onCreate() {

        if (this.policyId == null) {

            this.policyId = UUID.randomUUID().toString();
        }
        this.createdAt = LocalDate.now();
    }

    /******************************************* Getters and Setters *******************************************/

    // Getter and Setter for policyId
    public String getPolicyId() {
        return policyId;
    }
    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    // Getter and Setter for policyNo
    public String getPolicyNo() {
        return policyNo;
    }
    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    // Getter and Setter for policyName
    public String getPolicyName() {
        return policyName;
    }
    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    // Getter and Setter for type
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    // Getter and Setter for basePremium
    public double getBasePremium() {
        return basePremium;
    }
    public void setBasePremium(double basePremium) {
        this.basePremium = basePremium;
    }

    // Getter and Setter for features
    public String getFeatures() {
        return features;
    }
    public void setFeatures(String features) {
        this.features = features;
    }

    // Getter and Setter for addOns
    public String getAddOns() {
        return addOns;
    }
    public void setAddOns(String addOns) {
        this.addOns = addOns;
    }

    // Getter and Setter for startDate
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    // Getter and Setter for endDate
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    // Getter and Setter for renewalDate
    public LocalDate getRenewalDate() {
        return renewalDate;
    }
    public void setRenewalDate(LocalDate renewalDate) {
        this.renewalDate = renewalDate;
    }

    // Getter and Setter for status
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    // Getter and Setter for reminderSent
    public boolean isReminderSent() {
        return reminderSent;
    }
    public void setReminderSent(boolean reminderSent) {
        this.reminderSent = reminderSent;
    }

    // Getter for createdAt
    public LocalDate getCreatedAt() {
        return createdAt;
    }
}