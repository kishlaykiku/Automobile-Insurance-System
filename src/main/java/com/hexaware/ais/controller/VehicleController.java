package com.hexaware.ais.controller;

import java.util.List;

import com.hexaware.ais.dto.VehicleDTO;
import com.hexaware.ais.service.IVehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/*
 * @Author: Kishlay Kumar
 * Class: VehicleController
 * Description: This class handles HTTP requests related to vehicle operations
 */
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    /******************************************* Dependencies *******************************************/

    @Autowired
    private IVehicleService vehicleService;

    /******************************************* Endpoints *******************************************/

    // Create a new vehicle
    @PostMapping("/create")
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) {

        VehicleDTO createdVehicle = vehicleService.createVehicle(vehicleDTO);

        return ResponseEntity.ok(createdVehicle);
    }

    // Get a vehicle by ID
    @GetMapping("/get/{vehicleId}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable String vehicleId) {

        VehicleDTO vehicle = vehicleService.getVehicleById(vehicleId);

        return ResponseEntity.ok(vehicle);
    }

    // Get all vehicles
    @GetMapping("/getall")
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {

        List<VehicleDTO> vehicles = vehicleService.getAllVehicles();

        return ResponseEntity.ok(vehicles);
    }

    // Get vehicles by user ID
    @GetMapping("/get/vehicle-by-user/{userId}")
    public ResponseEntity<List<VehicleDTO>> getVehiclesByUserId(@PathVariable String userId) {

        List<VehicleDTO> vehicles = vehicleService.getVehiclesByUserId(userId);

        return ResponseEntity.ok(vehicles);
    }

    // Update a vehicle
    @PutMapping("/update/{vehicleId}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable String vehicleId, @RequestBody VehicleDTO vehicle) {

        VehicleDTO updatedVehicle = vehicleService.updateVehicle(vehicleId, vehicle);

        return ResponseEntity.ok(updatedVehicle);
    }

    // Delete a vehicle
    @DeleteMapping("/delete/{vehicleId}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String vehicleId) {

        vehicleService.deleteVehicle(vehicleId);

        return ResponseEntity.ok("Vehicle deleted successfully");
    }
}