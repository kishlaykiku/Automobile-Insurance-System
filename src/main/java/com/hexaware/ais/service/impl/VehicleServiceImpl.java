package com.hexaware.ais.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.hexaware.ais.entity.Vehicle;
import com.hexaware.ais.entity.User;
import com.hexaware.ais.dto.VehicleDTO;
import com.hexaware.ais.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    /******************************************* Methods *******************************************/

    @Override
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {

        if (vehicleDTO.getUserId() == null) {

            throw new IllegalArgumentException("User ID must not be null");
        }

        User user = userRepository.findById(vehicleDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + vehicleDTO.getUserId()));

        Vehicle vehicle = new Vehicle();

        vehicle.setType(vehicleDTO.getType());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setRegistrationNo(vehicleDTO.getRegistrationNo());
        vehicle.setYear(vehicleDTO.getYear());
        vehicle.setUser(user);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return new VehicleDTO(savedVehicle);
    }

    @Override
    public VehicleDTO getVehicleById(String vehicleId) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + vehicleId));

        return new VehicleDTO(vehicle);
    }

    @Override
    public List<VehicleDTO> getAllVehicles() {

        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<VehicleDTO> vehicleDTOs = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {

            vehicleDTOs.add(new VehicleDTO(vehicle));
        }

        return vehicleDTOs;
    }

    @Override
    public List<VehicleDTO> getVehiclesByUserId(String userId) {

        List<Vehicle> vehicles = vehicleRepository.findByUserUserId(userId);
        List<VehicleDTO> vehicleDTOs = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {

            vehicleDTOs.add(new VehicleDTO(vehicle));
        }

        return vehicleDTOs;
    }

    @Override
    public VehicleDTO updateVehicle(String vehicleId, VehicleDTO vehicleDTO) {

        Vehicle existingVehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + vehicleId));

        existingVehicle.setType(vehicleDTO.getType());
        existingVehicle.setModel(vehicleDTO.getModel());
        existingVehicle.setRegistrationNo(vehicleDTO.getRegistrationNo());
        existingVehicle.setYear(vehicleDTO.getYear());

        if (vehicleDTO.getUserId() != null) {

            User user = userRepository.findById(vehicleDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with ID: " + vehicleDTO.getUserId()));

            existingVehicle.setUser(user);
        }

        Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);

        return new VehicleDTO(updatedVehicle);
    }

    @Override
    public void deleteVehicle(String vehicleId) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with ID: " + vehicleId));

        vehicleRepository.delete(vehicle);
    }
}