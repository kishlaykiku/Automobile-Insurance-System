package com.hexaware.ais.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.hexaware.ais.entity.User;
import com.hexaware.ais.entity.Vehicle;
import com.hexaware.ais.dto.VehicleDTO;
import com.hexaware.ais.repository.UserRepository;
import com.hexaware.ais.repository.VehicleRepository;
import com.hexaware.ais.service.IVehicleService;
import com.hexaware.ais.exception.InvalidArgumentException;
import com.hexaware.ais.exception.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * @Author: Kishlay Kumar
 * Class: VehicleServiceImpl
 * Description: This class implements the IVehicleService interface for vehicle-related operations
 */
@Service
public class VehicleServiceImpl implements IVehicleService {

    /******************************************* Dependencies *******************************************/

    private static final Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    /******************************************* Methods *******************************************/

    @Override
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {

        if (vehicleDTO == null) {

            logger.error("VehicleDTO is null");
            throw new InvalidArgumentException("Vehicle data is required.");
        }

        logger.debug("[START] Creating vehicle for user ID: {}", vehicleDTO.getUserId());

        User user = userRepository.findById(vehicleDTO.getUserId())
                .orElseThrow(() -> {

                    logger.error("[END] User not found with ID: {}", vehicleDTO.getUserId());
                    return new ResourceNotFoundException("User not found with ID: " + vehicleDTO.getUserId());
                }
            );

        Vehicle vehicle = new Vehicle();

        vehicle.setType(vehicleDTO.getType());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setRegistrationNo(vehicleDTO.getRegistrationNo());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setUser(user);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        logger.debug("[END] Vehicle created successfully with ID: {}", savedVehicle.getVehicleId());

        return new VehicleDTO(savedVehicle);
    }

    @Override
    public VehicleDTO getVehicleById(String vehicleId) {

        if (vehicleId == null || vehicleId.isBlank()) {

            logger.error("Vehicle ID cannot be null or empty");
            throw new InvalidArgumentException("Vehicle ID is required.");
        }

        logger.debug("[START] Fetching vehicle with ID: {}", vehicleId);

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> {

                    logger.error("[END] Vehicle not found with ID: {}", vehicleId);
                    return new ResourceNotFoundException("Vehicle not found with ID: " + vehicleId);
                }
            );

        logger.debug("[END] Vehicle with ID ({}) fetched successfully", vehicleId);

        return new VehicleDTO(vehicle);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {

        logger.debug("[START] Fetching all vehicles");

        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<VehicleDTO> vehicleDTOs = new ArrayList<>();

        if(vehicles.isEmpty()) {

            logger.warn("[END] No vehicles found in the system");
            throw new ResourceNotFoundException("No vehicles found in the system.");
        }
        else {

            for (Vehicle vehicle : vehicles) {

                vehicleDTOs.add(new VehicleDTO(vehicle));
            }

            logger.debug("[END] Fetched all vehicles successfully");
        }

        return vehicleDTOs;
    }

    @Override
    public List<VehicleDTO> getVehiclesByUserId(String userId) {

        if (userId == null || userId.isBlank()) {

            logger.error("User ID cannot be null or empty");
            throw new InvalidArgumentException("User ID is required.");
        }

        logger.debug("[START] Fetching vehicles for user ID: {}", userId);

        List<Vehicle> vehicles = vehicleRepository.findByUserUserId(userId);
        List<VehicleDTO> vehicleDTOs = new ArrayList<>();

        if(vehicles.isEmpty()) {

            logger.warn("[END] No vehicles found for user ID: {}", userId);
            throw new ResourceNotFoundException("No vehicles found for user ID: " + userId);
        }
        else {

            for (Vehicle vehicle : vehicles) {

                vehicleDTOs.add(new VehicleDTO(vehicle));
            }

            logger.debug("[END] Fetched all vehicles for user ID ({}) successfully", userId);
        }

        return vehicleDTOs;
    }

    @Override
    public VehicleDTO updateVehicle(String vehicleId, VehicleDTO vehicleDTO) {

        logger.debug("[START] Updating vehicle with ID: {}", vehicleId);

        if (vehicleDTO == null) {

            logger.error("[END] Provided VehicleDTO is null");
            throw new InvalidArgumentException("Vehicle data is required.");
        }

        Vehicle existingVehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> {

                    logger.error("[END] Vehicle not found with ID: {}", vehicleId);
                    return new ResourceNotFoundException("Vehicle not found with ID: " + vehicleId);
                }
            );

        existingVehicle.setType(vehicleDTO.getType());
        existingVehicle.setModel(vehicleDTO.getModel());
        existingVehicle.setRegistrationNo(vehicleDTO.getRegistrationNo());
        existingVehicle.setYear(vehicleDTO.getYear());

        if (vehicleDTO.getUserId() != null) {

            User user = userRepository.findById(vehicleDTO.getUserId())
                    .orElseThrow(() -> {

                        logger.error("[END] User not found with ID: {}", vehicleDTO.getUserId());
                        return new ResourceNotFoundException("User not found with ID: " + vehicleDTO.getUserId());
                    }
                );

            existingVehicle.setUser(user);
        }

        Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);

        logger.debug("[END] Vehicle with ID ({}) updated successfully", vehicleId);

        return new VehicleDTO(updatedVehicle);
    }

    @Override
    public void deleteVehicle(String vehicleId) {

        if (vehicleId == null || vehicleId.isBlank()) {

            logger.error("Vehicle ID cannot be null or empty");
            throw new InvalidArgumentException("Vehicle ID is required.");
        }

        logger.debug("[START] Deleting vehicle with ID: {}", vehicleId);

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> {

                    logger.error("[END] Vehicle not found with ID: {}", vehicleId);
                    return new ResourceNotFoundException("Vehicle not found with ID: " + vehicleId);
                }
            );

        vehicleRepository.delete(vehicle);

        logger.debug("[END] Vehicle with ID ({}) deleted successfully", vehicleId);
    }
}