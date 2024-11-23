package com.hexaware.ais.dto;

import com.hexaware.ais.entity.Policy;

import java.time.LocalDate;

/*
 * @Author: Kishlay Kumar
 * Class: PolicyDTO
 * Description: Data Transfer Object for Policy entity to ensure separation of concerns
 */
public class PolicyDTO {

    /******************************************* Attributes *******************************************/

    private String policyId;
    private String policyNo;
    private String type;
    private double basePremium;
    private String features;
    private String addOns;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate renewalDate;
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
}