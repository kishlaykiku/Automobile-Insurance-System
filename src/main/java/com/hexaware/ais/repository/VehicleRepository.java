package com.hexaware.ais.repository;

import com.hexaware.ais.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * @Author: Kishlay Kumar
 * Class: Repository for Vehicle entity.
 * Description: This interface is used to interact with the Vehicle entity in the database.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    List<Vehicle> findByUserUserId(String userId);
}