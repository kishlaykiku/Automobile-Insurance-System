package com.hexaware.ais.service;

import java.util.List;

import com.hexaware.ais.entity.Policy;


/*
 * @Author: Kishlay Kumar
 * Interface: IPolicyService
 * Description: This interface defines the contract for policy-related operations
 */
public interface IPolicyService {

    /******************************************* Method Signatures *******************************************/

    // Create a new policy
    Policy createPolicy(Policy policy);

    // Get a policy by ID
    Policy getPolicyById(String policyId);

    // Get all policies
    List<Policy> getAllPolicies();

    // Fetch statistics
    long getActivePolicyCount();

    // Update a policy
    Policy updatePolicy(String policyId, Policy policy);

    // Delete a policy
    void deletePolicy(String policyId);

    // Fetch policies by user ID
    List<Policy> getPoliciesByUserId(String userId);

    // Sending premium reminders
    List<Policy> sendPremiumReminders();
}