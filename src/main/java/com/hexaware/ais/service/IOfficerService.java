package com.hexaware.ais.service;

import com.hexaware.ais.dto.OfficerDTO;

/*
 * @Author: Kishlay Kumar
 * Interface: IOfficerService
 * Description: Interface defining the contract for officer operations.
 */
public interface IOfficerService {

    // Retrieve admin details
    OfficerDTO getAdminDetails();

    // Update admin details
    OfficerDTO updateAdminDetails(OfficerDTO officerDTO);

    // Authenticate admin credentials
    boolean authenticateAdmin(String email, String password);
}