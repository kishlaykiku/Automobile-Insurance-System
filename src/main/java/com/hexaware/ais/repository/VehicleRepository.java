package com.hexaware.ais.repository;

import java.util.List;

import com.hexaware.ais.entity.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*
 * @Author: Kishlay Kumar
 * Class: Repository for Vehicle entity.
 * Description: This interface is used to interact with the Vehicle entity in the database.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    // [SELECT * FROM vehicle WHERE user_id = ?]
    List<Vehicle> findByUserUserId(String userId);
}