package com.hexaware.ais.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import com.hexaware.ais.entity.Policy;
import com.hexaware.ais.dto.PolicyDTO;
import com.hexaware.ais.repository.PolicyRepository;
import com.hexaware.ais.service.IPolicyService;
import com.hexaware.ais.exception.InvalidArgumentException;
import com.hexaware.ais.exception.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(PolicyServiceImpl.class);

    @Autowired
    private PolicyRepository policyRepository;

    /******************************************* Methods *******************************************/

    @Override
    public PolicyDTO createPolicy(PolicyDTO policyDTO) {

        if(policyDTO == null) {

            logger.error("PolicyDTO is null");
            throw new InvalidArgumentException("Policy data is required.");
        }

        logger.debug("[START] Creating policy with policy number: {}", policyDTO.getPolicyNo());

        Policy policy = new Policy();

        policy.setPolicyNo(policyDTO.getPolicyNo());
        policy.setPolicyName(policyDTO.getPolicyName());
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

        logger.debug("[END] Policy created successfully with ID: {}", savedPolicy.getPolicyId());

        return new PolicyDTO(savedPolicy);
    }

    @Override
    public PolicyDTO getPolicyById(String policyId) {

        if (policyId == null || policyId.isBlank()) {

            logger.error("Policy ID cannot be null or empty");
            throw new InvalidArgumentException("Policy ID is required.");
        }

        logger.debug("[START] Fetching policy with ID: {}", policyId);

        Policy policy = policyRepository.findById(policyId)
                .orElseThrow(() -> {

                    logger.error("[END] Policy with ID ({}) not found", policyId);
                    return new ResourceNotFoundException("Policy not found with ID: " + policyId);
                }
            );

        logger.debug("[END] Policy with ID ({}) fetched successfully", policyId);

        return new PolicyDTO(policy);
    }

    @Override
    public List<PolicyDTO> getAllPolicies() {

        logger.debug("[START] Fetching all policies");

        List<Policy> policies = policyRepository.findAll();
        List<PolicyDTO> policyDTOs = new ArrayList<>();

        if(policies.isEmpty()) {

            logger.warn("[END] No policies found in the system");
            return new ArrayList<>();
        }
        else {

            for (Policy policy : policies) {

                policyDTOs.add(new PolicyDTO(policy));
            }

            logger.debug("[END] Fetched all policies successfully");
        }

        return policyDTOs;
    }

    @Override
    public long getActivePolicyCount() {

        logger.debug("[START] Fetching active policy count");

        long count = policyRepository.countByStatus("Active");

        logger.debug("[END] Active policy count: {}", count);

        return count;
    }

    @Override
    public PolicyDTO updatePolicy(String policyId, PolicyDTO policyDTO) {

        logger.debug("[START] Updating policy with ID: {}", policyId);

        if(policyDTO == null) {

            logger.error("[END] PolicyDTO is null");
            throw new InvalidArgumentException("Policy data is required.");
        }

        if (policyId == null || policyId.isBlank()) {

            logger.error("[END] Policy ID cannot be null or empty");
            throw new InvalidArgumentException("Policy ID is required.");
        }

        Policy existingPolicy = policyRepository.findById(policyId)
                .orElseThrow(() -> {

                    logger.error("[END] Policy with ID ({}) not found", policyId);
                    return new RuntimeException("Policy not found with ID: " + policyId);
                }
            );

        existingPolicy.setPolicyNo(policyDTO.getPolicyNo());
        existingPolicy.setPolicyName(policyDTO.getPolicyName());
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

        logger.debug("[END] Policy with ID ({}) updated successfully", policyId);

        return new PolicyDTO(updatedPolicy);
    }

    @Override
    public void deletePolicy(String policyId) {

        if (policyId == null || policyId.isBlank()) {

            logger.error("Policy ID cannot be null or empty");
            throw new InvalidArgumentException("Policy ID is required.");
        }

        logger.debug("[START] Deleting policy with ID: {}", policyId);

        Policy existingPolicy = policyRepository.findById(policyId)
                .orElseThrow(() -> {

                    logger.error("[END] Policy with ID ({}) not found", policyId);
                    return new ResourceNotFoundException("Policy not found with ID: " + policyId);
                }
            );

        policyRepository.delete(existingPolicy);

        logger.debug("[END] Policy with ID ({}) deleted successfully", policyId);
    }

    @Override
    public List<PolicyDTO> getPoliciesByUserId(String userId) {

        if (userId == null || userId.isBlank()) {

            logger.error("User ID cannot be null or empty");
            throw new InvalidArgumentException("User ID is required.");
        }

        logger.debug("[START] Fetching policies for user with ID: {}", userId);

        List<Policy> policies = policyRepository.findPoliciesByUserId(userId);
        List<PolicyDTO> policyDTOs = new ArrayList<>();

        if(policies.isEmpty()) {

            logger.warn("[END] No policies found for user with ID: {}", userId);
            return new ArrayList<>();
        }
        else {

            for (Policy policy : policies) {

                policyDTOs.add(new PolicyDTO(policy));
            }

            logger.debug("[END] Fetched policies for user with ID ({}) successfully", userId);
        }

        return policyDTOs;
    }

    @Override
    public List<PolicyDTO> sendPremiumReminders() {

        logger.debug("[START] Sending premium reminders");

        LocalDate today = LocalDate.now();
        LocalDate oneWeekLater = today.plusWeeks(1);

        List<Policy> expiringPolicies = policyRepository.findByEndDateBetweenAndReminderSentFalse(today, oneWeekLater);
        List<PolicyDTO> policyDTOs = new ArrayList<>();

        if(expiringPolicies.isEmpty()) {

            logger.warn("[END] No expiring policies found");
        }
        else {

            for (Policy policy : expiringPolicies) {

                policy.setReminderSent(true);
                policy.setStatus("Reminder Sent");
    
                policyRepository.save(policy);
    
                logger.info("Reminder sent for policy ({}) with expiry date ({})", policy.getPolicyNo(), policy.getEndDate());
    
                policyDTOs.add(new PolicyDTO(policy));
            }

            logger.debug("[END] Premium reminders sent successfully");
        }

        return policyDTOs;
    }
}