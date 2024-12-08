package com.hexaware.ais.service;

import java.util.List;

import com.hexaware.ais.dto.PolicyDTO;


/*
 * @Author: Kishlay Kumar
 * Interface: IPolicyService
 * Description: This interface defines the contract for policy-related operations
 */
public interface IPolicyService {

    /******************************************* Method Signatures *******************************************/

    // Create a new policy
    PolicyDTO createPolicy(PolicyDTO policyDTO);

    // Get a policy by ID
    PolicyDTO getPolicyById(String policyId);

    // Get all policies
    List<PolicyDTO> getAllPolicies();

    // Fetch statistics
    long getActivePolicyCount();

    // Update a policy
    PolicyDTO updatePolicy(String policyId, PolicyDTO policyDTO);

    // Delete a policy
    void deletePolicy(String policyId);

    // Fetch policies by user ID
    List<PolicyDTO> getPoliciesByUserId(String userId);

    // Sending premium reminders
    List<PolicyDTO> sendPremiumReminders();
}