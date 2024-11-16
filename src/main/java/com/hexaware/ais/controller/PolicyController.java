package com.hexaware.ais.controller;

import java.util.List;

import com.hexaware.ais.entity.Policy;
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
    @PostMapping
    public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy) {

        Policy createdPolicy = policyService.createPolicy(policy);

        return ResponseEntity.ok(createdPolicy);
    }

    // Get a policy by ID
    @GetMapping("/{policyId}")
    public ResponseEntity<Policy> getPolicyById(@PathVariable String policyId) {

        Policy policy = policyService.getPolicyById(policyId);

        return ResponseEntity.ok(policy);
    }

    // Get all policies
    @GetMapping
    public ResponseEntity<List<Policy>> getAllPolicies() {

        List<Policy> policies = policyService.getAllPolicies();

        return ResponseEntity.ok(policies);
    }

    // Update a policy
    @PutMapping("/{policyId}")
    public ResponseEntity<Policy> updatePolicy(@PathVariable String policyId, @RequestBody Policy policy) {

        Policy updatedPolicy = policyService.updatePolicy(policyId, policy);

        return ResponseEntity.ok(updatedPolicy);
    }

    // Delete a policy
    @DeleteMapping("/{policyId}")
    public ResponseEntity<String> deletePolicy(@PathVariable String policyId) {

        policyService.deletePolicy(policyId);

        return ResponseEntity.ok("Policy deleted successfully");
    }
}