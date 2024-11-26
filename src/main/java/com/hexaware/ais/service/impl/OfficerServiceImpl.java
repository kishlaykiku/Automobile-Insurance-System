package com.hexaware.ais.service.impl;

import java.util.Optional;

import com.hexaware.ais.entity.Officer;
import com.hexaware.ais.dto.OfficerDTO;
import com.hexaware.ais.repository.OfficerRepository;
import com.hexaware.ais.service.IOfficerService;
import com.hexaware.ais.exception.InvalidArgumentException;
import com.hexaware.ais.exception.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * @Author: Kishlay Kumar
 * Class: OfficerServiceImpl
 * Description: Implements the IOfficerService interface for officer-related operations.
 */
@Service
public class OfficerServiceImpl implements IOfficerService {

    /******************************************* Dependencies *******************************************/

    private static final Logger logger = LoggerFactory.getLogger(OfficerServiceImpl.class);

    @Autowired
    private OfficerRepository officerRepository;

    /******************************************* Methods *******************************************/

    @Override
    public OfficerDTO getAdminDetails() {

        logger.debug("[START] Fetching admin details");

        Officer officer = officerRepository.findByRole("Admin")
                .orElseThrow(() -> {

                    logger.error("[END] Admin not found");
                    return new ResourceNotFoundException("Admin not found.");
                }
            );

        logger.debug("[END] Admin details fetched successfully");

        return new OfficerDTO(officer);
    }

    @Override
    public OfficerDTO updateAdminDetails(OfficerDTO officerDTO) {

        logger.debug("[START] Updating admin details");

        if (officerDTO == null) {

            logger.error("[END] Provided OfficerDTO is null");
            throw new InvalidArgumentException("Officer details are required.");
        }    

        Officer existingAdmin = officerRepository.findByRole("Admin")
                .orElseThrow(() -> {

                    logger.error("[END] Admin not found");
                    return new ResourceNotFoundException("Admin not found.");
                }
            );

        existingAdmin.setName(officerDTO.getName());
        existingAdmin.setEmail(officerDTO.getEmail());

        officerRepository.save(existingAdmin);

        logger.debug("[END] Admin details updated successfully");

        return new OfficerDTO(existingAdmin);
    }

    @Override
    public boolean authenticateAdmin(String email, String password) {

        logger.debug("[START] Authenticating admin with email: {}", email);

        if (email == null || password == null) {
            logger.error("[END] Email and Password must not be null");
            throw new InvalidArgumentException("Email and Password must not be empty.");
        }

        Optional<Officer> adminOptional = officerRepository.findByEmail(email);

        if (adminOptional.isPresent()) {

            Officer admin = adminOptional.get();

            if (admin.getPassword().equals(password)) {

                logger.debug("[END] Admin authentication successful for email: {}", email);

                return true;
            }
            else {

                logger.warn("[END] Invalid password for email: {}", email);

                return false;
            }
        }

        logger.warn("[END] Admin with email ({}) not found", email);

        return false;
    }
}