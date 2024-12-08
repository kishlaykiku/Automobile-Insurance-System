package com.hexaware.ais.entity;

import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


/*
 * @Author: Kishlay Kumar
 * Class: Vehicle Entity
 * Description: This class is used to represent the Vehicle entity
 * Map: It is mapped to the `vehicle` table in the database
 */
@Entity
@Table(name = "vehicle")
public class Vehicle {

    /******************************************* Attributes *******************************************/

    @Id
    @Column(name = "vehicle_id", nullable = false, unique = true)
    private String vehicleId;

    @NotBlank(message = "Vehicle type is required")
    @Column(name = "type", nullable = false)
    private String type;

    @NotBlank(message = "Vehicle model is required")
    @Column(name = "model", nullable = false)
    private String model;

    @NotBlank(message = "Registration number is required")
    @Column(name = "registration_no", nullable = false, unique = true)
    private String registrationNo;

    @Min(value = 1886, message = "Year must be after 1886")
    @Column(name = "year", nullable = false)
    private int year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    protected void generateId() {

        if (this.vehicleId == null) {

            this.vehicleId = UUID.randomUUID().toString();
        }
    }

    /******************************************* Getters and Setters *******************************************/

    // Getter and Setter for vehicleId
    public String getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    // Getter and Setter for type
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    // Getter and Setter for model
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    // Getter and Setter for registrationNo
    public String getRegistrationNo() {
        return registrationNo;
    }
    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    // Getter and Setter for year
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    // Getter and Setter for user
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}