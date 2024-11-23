package com.hexaware.ais.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import com.hexaware.ais.entity.Policy;
import com.hexaware.ais.dto.PolicyDTO;
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
    public PolicyDTO createPolicy(PolicyDTO policyDTO) {

        Policy policy = new Policy();

        policy.setPolicyNo(policyDTO.getPolicyNo());
        policy.setType(policyDTO.getType());
        policy.setBasePremium(policyDTO.getBasePremium());
        policy.setFeatures(policyDTO.getFeatures());
        policy.setAddOns(policyDTO.getAddOns());
        policy.setStartDate(policyDTO.getStartDate());
        policy.setEndDate(policyDTO.getEndDate());
        policy.setRenewalDate(policyDTO.getRenewalDate());
        policy.setStatus(policyDTO.getStatus());
        policy.setReminderSent(policyDTO.isReminderSent());

        Policy savedPolicy = policyRepository.save(policy);

        return new PolicyDTO(savedPolicy);
    }

    @Override
    public PolicyDTO getPolicyById(String policyId) {

        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found with ID: " + policyId));

        return new PolicyDTO(policy);
    }

    @Override
    public List<PolicyDTO> getAllPolicies() {

        List<Policy> policies = policyRepository.findAll();
        List<PolicyDTO> policyDTOs = new ArrayList<>();

        for (Policy policy : policies) {

            policyDTOs.add(new PolicyDTO(policy));
        }

        return policyDTOs;
    }

    @Override
    public long getActivePolicyCount() {

        return policyRepository.countByStatus("Active");
    }

    @Override
    public PolicyDTO updatePolicy(String policyId, PolicyDTO policyDTO) {

        Policy existingPolicy = policyRepository.findById(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found with ID: " + policyId));

        existingPolicy.setPolicyNo(policyDTO.getPolicyNo());
        existingPolicy.setType(policyDTO.getType());
        existingPolicy.setBasePremium(policyDTO.getBasePremium());
        existingPolicy.setFeatures(policyDTO.getFeatures());
        existingPolicy.setAddOns(policyDTO.getAddOns());
        existingPolicy.setStartDate(policyDTO.getStartDate());
        existingPolicy.setEndDate(policyDTO.getEndDate());
        existingPolicy.setRenewalDate(policyDTO.getRenewalDate());
        existingPolicy.setStatus(policyDTO.getStatus());
        existingPolicy.setReminderSent(policyDTO.isReminderSent());

        Policy updatedPolicy = policyRepository.save(existingPolicy);

        return new PolicyDTO(updatedPolicy);
    }

    @Override
    public void deletePolicy(String policyId) {

        Policy existingPolicy = policyRepository.findById(policyId)
                .orElseThrow(() -> new RuntimeException("Policy not found with ID: " + policyId));

        policyRepository.delete(existingPolicy);
    }

    @Override
    public List<PolicyDTO> getPoliciesByUserId(String userId) {

        List<Policy> policies = policyRepository.findPoliciesByUserId(userId);
        List<PolicyDTO> policyDTOs = new ArrayList<>();

        for (Policy policy : policies) {

            policyDTOs.add(new PolicyDTO(policy));
        }

        return policyDTOs;
    }

    @Override
    public List<PolicyDTO> sendPremiumReminders() {

        LocalDate today = LocalDate.now();
        LocalDate oneWeekLater = today.plusWeeks(1);

        List<Policy> expiringPolicies = policyRepository.findByEndDateBetweenAndReminderSentFalse(today, oneWeekLater);
        List<PolicyDTO> policyDTOs = new ArrayList<>();

        for (Policy policy : expiringPolicies) {

            policy.setReminderSent(true);
            policy.setStatus("Reminder Sent");

            policyRepository.save(policy);

            System.out.println("Reminder sent for policy: " + policy.getPolicyNo() + ", Expiry Date: " + policy.getEndDate());

            policyDTOs.add(new PolicyDTO(policy));
        }

        return policyDTOs;
    }
}