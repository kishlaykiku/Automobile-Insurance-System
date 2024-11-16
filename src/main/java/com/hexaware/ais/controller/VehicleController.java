package com.hexaware.ais.controller;

import java.util.List;

import com.hexaware.ais.entity.Vehicle;
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
    @PostMapping
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle) {

        Vehicle createdVehicle = vehicleService.createVehicle(vehicle);

        return ResponseEntity.ok(createdVehicle);
    }

    // Get a vehicle by ID
    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable String vehicleId) {

        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);

        return ResponseEntity.ok(vehicle);
    }

    // Get all vehicles
    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {

        List<Vehicle> vehicles = vehicleService.getAllVehicles();

        return ResponseEntity.ok(vehicles);
    }

    // Get vehicles by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByUserId(@PathVariable String userId) {

        List<Vehicle> vehicles = vehicleService.getVehiclesByUserId(userId);

        return ResponseEntity.ok(vehicles);
    }

    // Update a vehicle
    @PutMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable String vehicleId, @RequestBody Vehicle vehicle) {

        Vehicle updatedVehicle = vehicleService.updateVehicle(vehicleId, vehicle);

        return ResponseEntity.ok(updatedVehicle);
    }

    // Delete a vehicle
    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<String> deleteVehicle(@PathVariable String vehicleId) {

        vehicleService.deleteVehicle(vehicleId);

        return ResponseEntity.ok("Vehicle deleted successfully");
    }
}