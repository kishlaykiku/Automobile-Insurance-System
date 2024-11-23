package com.hexaware.ais.dto;

import com.hexaware.ais.entity.Vehicle;

/*
 * @Author: Kishlay Kumar
 * Class: VehicleDTO
 * Description: Data Transfer Object for Vehicle entity to manage data exposure
 */
public class VehicleDTO {

    private String vehicleId;
    private String type;
    private String model;
    private String registrationNo;
    private int year;
    private String userId;

    /******************************************* Constructor *******************************************/

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
}