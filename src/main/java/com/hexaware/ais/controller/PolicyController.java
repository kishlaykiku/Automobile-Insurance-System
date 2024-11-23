package com.hexaware.ais.controller;

import java.util.List;

import com.hexaware.ais.dto.PolicyDTO;
import com.hexaware.ais.service.IPolicyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/*
 * @Author: Kishlay Kumar
 * Class: PolicyController
 * Description: This class handles HTTP requests related to policy operations
 */
@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    /******************************************* Dependencies *******************************************/

    @Autowired
    private IPolicyService policyService;

    /******************************************* Endpoints *******************************************/

    // Create a new policy
    @PostMapping("/create")
    public ResponseEntity<PolicyDTO> createPolicy(@RequestBody PolicyDTO policyDTO) {

        PolicyDTO createdPolicy = policyService.createPolicy(policyDTO);

        return ResponseEntity.ok(createdPolicy);
    }

    // Get a policy by ID
    @GetMapping("/get/{policyId}")
    public ResponseEntity<PolicyDTO> getPolicyById(@PathVariable String policyId) {

        PolicyDTO policy = policyService.getPolicyById(policyId);

        return ResponseEntity.ok(policy);
    }

    // Get all policies
    @GetMapping("/getall")
    public ResponseEntity<List<PolicyDTO>> getAllPolicies() {

        List<PolicyDTO> policies = policyService.getAllPolicies();

        return ResponseEntity.ok(policies);
    }

    // Fetch active policy count
    @GetMapping("/get/statistics/active-policy")
    public ResponseEntity<Long> getActivePolicyCount() {

        long activePolicyCount = policyService.getActivePolicyCount();

        return ResponseEntity.ok(activePolicyCount);
    }

    // Update a policy
    @PutMapping("/update/{policyId}")
    public ResponseEntity<PolicyDTO> updatePolicy(@PathVariable String policyId, @RequestBody PolicyDTO policyDTO) {

        PolicyDTO updatedPolicy = policyService.updatePolicy(policyId, policyDTO);

        return ResponseEntity.ok(updatedPolicy);
    }

    // Delete a policy
    @DeleteMapping("/delete/{policyId}")
    public ResponseEntity<String> deletePolicy(@PathVariable String policyId) {

        policyService.deletePolicy(policyId);

        return ResponseEntity.ok("Policy deleted successfully");
    }

    // Get all policies for a user
    @GetMapping("/get/policy-by-user/{userId}")
    public ResponseEntity<List<PolicyDTO>> getPoliciesByUserId(@PathVariable String userId) {

        List<PolicyDTO> policies = policyService.getPoliciesByUserId(userId);

        return ResponseEntity.ok(policies);
    }

    // Send premium reminders
    @PutMapping("/send-premium-reminders")
    public ResponseEntity<List<PolicyDTO>> sendPremiumReminders() {

        List<PolicyDTO> policies = policyService.sendPremiumReminders();

        return ResponseEntity.ok(policies);
    }
}