package com.hexaware.ais.service;

import java.util.List;

import com.hexaware.ais.entity.Vehicle;


/*
 * @Author: Kishlay Kumar
 * Interface: IVehicleService
 * Description: This interface defines the contract for vehicle-related operations
 */
public interface IVehicleService {

    /******************************************* Method Signatures *******************************************/

    // Create a new vehicle
    Vehicle createVehicle(Vehicle vehicle);

    // Get a vehicle by ID
    Vehicle getVehicleById(String vehicleId);

    // Get all vehicles
    List<Vehicle> getAllVehicles();

    // Get vehicles by user ID
    List<Vehicle> getVehiclesByUserId(String userId);

    // Update a vehicle
    Vehicle updateVehicle(String vehicleId, Vehicle vehicle);

    // Delete a vehicle
    void deleteVehicle(String vehicleId);
}