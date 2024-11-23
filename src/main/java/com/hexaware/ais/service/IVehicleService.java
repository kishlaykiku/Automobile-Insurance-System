package com.hexaware.ais.service;

import java.util.List;

import com.hexaware.ais.dto.VehicleDTO;


/*
 * @Author: Kishlay Kumar
 * Interface: IVehicleService
 * Description: This interface defines the contract for vehicle-related operations
 */
public interface IVehicleService {

    /******************************************* Method Signatures *******************************************/

    // Create a new vehicle
    VehicleDTO createVehicle(VehicleDTO vehicleDTO);

    // Get a vehicle by ID
    VehicleDTO getVehicleById(String vehicleId);

    // Get all vehicles
    List<VehicleDTO> getAllVehicles();

    // Get vehicles by user ID
    List<VehicleDTO> getVehiclesByUserId(String userId);

    // Update a vehicle
    VehicleDTO updateVehicle(String vehicleId, VehicleDTO vehicleDTO);

    // Delete a vehicle
    void deleteVehicle(String vehicleId);
}