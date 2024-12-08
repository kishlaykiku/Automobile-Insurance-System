package com.hexaware.ais.dto;

import com.hexaware.ais.entity.User;
import com.hexaware.ais.entity.Vehicle;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/*
 * @Author: Kishlay Kumar
 * Class: VehicleDTO
 * Description: Data Transfer Object for Vehicle entity to manage data exposure
 */
public class VehicleDTO {

    /******************************************* Attributes *******************************************/

    private String vehicleId;

    @NotBlank(message = "Vehicle type is required")
    private String type;

    @NotBlank(message = "Vehicle model is required")
    private String model;

    @NotBlank(message = "Registration number is required")
    private String registrationNo;

    @Min(value = 1886, message = "Year must be after 1886")
    private int year;

    private String userId;

    /******************************************* Constructors *******************************************/

    // Default constructor
    public VehicleDTO() {

        super();
    }

    // Constructor to map Vehicle entity to VehicleDTO
    public VehicleDTO(Vehicle vehicle) {

        super();

        this.vehicleId = vehicle.getVehicleId();
        this.type = vehicle.getType();
        this.model = vehicle.getModel();
        this.registrationNo = vehicle.getRegistrationNo();
        this.year = vehicle.getYear();
        this.userId = vehicle.getUser() != null ? vehicle.getUser().getUserId() : null;
    }

    /******************************************* Getters and Setters *******************************************/

    public String getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }
    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /******************************************* Utility Methods *******************************************/

    // Convert DTO to Entity
    public Vehicle toEntity(User user) {

        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleId(this.vehicleId);
        vehicle.setType(this.type);
        vehicle.setModel(this.model);
        vehicle.setRegistrationNo(this.registrationNo);
        vehicle.setYear(this.year);
        vehicle.setUser(user);

        return vehicle;
    }
}