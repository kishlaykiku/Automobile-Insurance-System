package com.hexaware.ais.dto;

import com.hexaware.ais.entity.Policy;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


/*
 * @Author: Kishlay Kumar
 * Class: PolicyDTO
 * Description: Data Transfer Object for Policy entity to ensure separation of concerns
 */
public class PolicyDTO {

    /******************************************* Attributes *******************************************/

    private String policyId;

    @NotBlank(message = "Policy number is required")
    private String policyNo;

    @NotBlank(message = "Policy name is required")
    private String policyName;

    @NotBlank(message = "Policy type is required")
    private String type;

    @NotNull(message = "Base premium is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Base premium must be greater than 0")
    private double basePremium;

    private String features;

    private String addOns;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be today or in the future")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    private LocalDate endDate;

    private LocalDate renewalDate;

    @NotBlank(message = "Status is required")
    private String status;

    private boolean reminderSent;

    /******************************************* Constructors *******************************************/

    // Default constructor
    public PolicyDTO() {

        super();
    }

    // Constructor for Policy to PolicyDTO mapping
    public PolicyDTO(Policy policy) {

        super();

        this.policyId = policy.getPolicyId();
        this.policyNo = policy.getPolicyNo();
        this.policyName = policy.getPolicyName();
        this.type = policy.getType();
        this.basePremium = policy.getBasePremium();
        this.features = policy.getFeatures();
        this.addOns = policy.getAddOns();
        this.startDate = policy.getStartDate();
        this.endDate = policy.getEndDate();
        this.renewalDate = policy.getRenewalDate();
        this.status = policy.getStatus();
        this.reminderSent = policy.isReminderSent();
    }

    /******************************************* Getters and Setters *******************************************/

    public String getPolicyId() {
        return policyId;
    }
    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getPolicyNo() {
        return policyNo;
    }
    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getPolicyName() {
        return policyName;
    }
    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public double getBasePremium() {
        return basePremium;
    }
    public void setBasePremium(double basePremium) {
        this.basePremium = basePremium;
    }

    public String getFeatures() {
        return features;
    }
    public void setFeatures(String features) {
        this.features = features;
    }

    public String getAddOns() {
        return addOns;
    }
    public void setAddOns(String addOns) {
        this.addOns = addOns;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getRenewalDate() {
        return renewalDate;
    }
    public void setRenewalDate(LocalDate renewalDate) {
        this.renewalDate = renewalDate;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isReminderSent() {
        return reminderSent;
    }
    public void setReminderSent(boolean reminderSent) {
        this.reminderSent = reminderSent;
    }

    /******************************************* Utility Methods *******************************************/

    // Convert DTO to Entity
    public Policy toEntity() {

        Policy policy = new Policy();

        policy.setPolicyId(this.policyId);
        policy.setPolicyNo(this.policyNo);
        policy.setPolicyName(this.policyName);
        policy.setType(this.type);
        policy.setBasePremium(this.basePremium);
        policy.setFeatures(this.features);
        policy.setAddOns(this.addOns);
        policy.setStartDate(this.startDate);
        policy.setEndDate(this.endDate);
        policy.setRenewalDate(this.renewalDate);
        policy.setStatus(this.status);
        policy.setReminderSent(this.reminderSent);

        return policy;
    }
}