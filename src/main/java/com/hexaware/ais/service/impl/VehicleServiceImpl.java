package com.hexaware.ais.service.impl;

import java.util.List;
import java.util.Optional;

import com.hexaware.ais.entity.Vehicle;
import com.hexaware.ais.repository.VehicleRepository;
import com.hexaware.ais.service.IVehicleService;

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

    @Autowired
    private VehicleRepository vehicleRepository;

    /******************************************* Methods *******************************************/

    @Override
    public Vehicle createVehicle(Vehicle vehicle) {

        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getVehicleById(String vehicleId) {

        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);

        return vehicle.orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + vehicleId));
    }

    @Override
    public List<Vehicle> getAllVehicles() {

        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> getVehiclesByUserId(String userId) {

        return vehicleRepository.findByUserUserId(userId);
    }

    @Override
    public Vehicle updateVehicle(String vehicleId, Vehicle updatedVehicle) {

        Vehicle existingVehicle = getVehicleById(vehicleId);

        existingVehicle.setType(updatedVehicle.getType());
        existingVehicle.setModel(updatedVehicle.getModel());
        existingVehicle.setRegistrationNo(updatedVehicle.getRegistrationNo());
        existingVehicle.setYear(updatedVehicle.getYear());

        return vehicleRepository.save(existingVehicle);
    }

    @Override
    public void deleteVehicle(String vehicleId) {

        Vehicle existingVehicle = getVehicleById(vehicleId);

        vehicleRepository.delete(existingVehicle);
    }
}