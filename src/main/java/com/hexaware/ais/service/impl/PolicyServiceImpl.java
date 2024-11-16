package com.hexaware.ais.service.impl;

import java.util.List;
import java.util.Optional;

import com.hexaware.ais.entity.Policy;
import com.hexaware.ais.repository.PolicyRepository;
import com.hexaware.ais.service.IPolicyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * @Author: Kishlay Kumar
 * Class: PolicyServiceImpl
 * Description: This class implements the IPolicyService interface for policy-related operations
 */
@Service
public class PolicyServiceImpl implements IPolicyService {

    /******************************************* Dependencies *******************************************/

    @Autowired
    private PolicyRepository policyRepository;

    /******************************************* Methods *******************************************/

    @Override
    public Policy createPolicy(Policy policy) {

        return policyRepository.save(policy);
    }

    @Override
    public Policy getPolicyById(String policyId) {

        Optional<Policy> policy = policyRepository.findById(policyId);

        return policy.orElseThrow(() -> new RuntimeException("Policy not found with ID: " + policyId));
    }

    @Override
    public List<Policy> getAllPolicies() {

        return policyRepository.findAll();
    }

    @Override
    public Policy updatePolicy(String policyId, Policy updatedPolicy) {

        Policy existingPolicy = getPolicyById(policyId);

        existingPolicy.setPolicyNo(updatedPolicy.getPolicyNo());
        existingPolicy.setType(updatedPolicy.getType());
        existingPolicy.setBasePremium(updatedPolicy.getBasePremium());
        existingPolicy.setFeatures(updatedPolicy.getFeatures());
        existingPolicy.setAddOns(updatedPolicy.getAddOns());
        existingPolicy.setStartDate(updatedPolicy.getStartDate());
        existingPolicy.setEndDate(updatedPolicy.getEndDate());
        existingPolicy.setRenewalDate(updatedPolicy.getRenewalDate());
        existingPolicy.setStatus(updatedPolicy.getStatus());
        existingPolicy.setReminderSent(updatedPolicy.isReminderSent());

        return policyRepository.save(existingPolicy);
    }

    @Override
    public void deletePolicy(String policyId) {

        Policy existingPolicy = getPolicyById(policyId);

        policyRepository.delete(existingPolicy);
    }
}