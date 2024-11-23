package com.hexaware.ais.service.impl;

import java.util.Optional;

import com.hexaware.ais.entity.Officer;
import com.hexaware.ais.dto.OfficerDTO;
import com.hexaware.ais.repository.OfficerRepository;
import com.hexaware.ais.service.IOfficerService;

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

    @Autowired
    private OfficerRepository officerRepository;

    /******************************************* Methods *******************************************/

    @Override
    public OfficerDTO getAdminDetails() {

        Officer officer = officerRepository.findByRole("Admin")
                .orElseThrow(() -> new RuntimeException("Admin not found."));

        return new OfficerDTO(officer);
    }

    @Override
    public OfficerDTO updateAdminDetails(OfficerDTO officerDTO) {

        Officer existingAdmin = officerRepository.findByRole("Admin")
                .orElseThrow(() -> new RuntimeException("Admin not found."));

        existingAdmin.setName(officerDTO.getName());
        existingAdmin.setEmail(officerDTO.getEmail());

        officerRepository.save(existingAdmin);

        return new OfficerDTO(existingAdmin);
    }

    @Override
    public boolean authenticateAdmin(String email, String password) {

        Optional<Officer> adminOptional = officerRepository.findByEmail(email);

        if (adminOptional.isPresent()) {

            Officer admin = adminOptional.get();

            return admin.getPassword().equals(password);
        }

        return false;
    }
}