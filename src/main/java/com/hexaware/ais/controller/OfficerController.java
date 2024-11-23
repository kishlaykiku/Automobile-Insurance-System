package com.hexaware.ais.controller;

import com.hexaware.ais.dto.OfficerDTO;
import com.hexaware.ais.service.IOfficerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * @Author: Kishlay Kumar
 * Class: OfficerController
 * Description: Handles HTTP requests related to the admin (officer) operations.
 */
@RestController
@RequestMapping("/api/officer")
public class OfficerController {

    /******************************************* Dependencies *******************************************/

    @Autowired
    private IOfficerService officerService;

    /******************************************* Endpoints *******************************************/

    // Retrieve admin details
    @GetMapping("/admin-details")
    public ResponseEntity<OfficerDTO> getAdminDetails() {

        OfficerDTO adminDetails = officerService.getAdminDetails();

        return ResponseEntity.ok(adminDetails);
    }

    // Update admin details
    @PutMapping("/update-admin")
    public ResponseEntity<OfficerDTO> updateAdminDetails(@RequestBody OfficerDTO officerDTO) {

        OfficerDTO updatedAdmin = officerService.updateAdminDetails(officerDTO);

        return ResponseEntity.ok(updatedAdmin);
    }

    // Authenticate admin credentials
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateAdmin(@RequestParam String email, @RequestParam String password) {

        boolean isAuthenticated = officerService.authenticateAdmin(email, password);

        if (isAuthenticated) {
    
            return ResponseEntity.ok("Authentication successful.");
        }
        else {

            return ResponseEntity.status(401).body("Invalid credentials.");
        }
    }
}