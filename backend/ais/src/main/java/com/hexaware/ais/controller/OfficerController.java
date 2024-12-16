package com.hexaware.ais.controller;

import com.hexaware.ais.dto.OfficerDTO;
import com.hexaware.ais.service.IOfficerService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('OFFICER')")
    @GetMapping("/admin-details")
    public ResponseEntity<OfficerDTO> getAdminDetails() {

        OfficerDTO adminDetails = officerService.getAdminDetails();

        return ResponseEntity.ok(adminDetails);
    }

    // Update admin details
    @PreAuthorize("hasRole('OFFICER')")
    @PutMapping("/update-admin")
    public ResponseEntity<OfficerDTO> updateAdminDetails(@Valid @RequestBody OfficerDTO officerDTO) {

        OfficerDTO updatedAdmin = officerService.updateAdminDetails(officerDTO);

        return ResponseEntity.ok(updatedAdmin);
    }
}