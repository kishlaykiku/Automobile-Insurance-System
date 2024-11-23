package com.hexaware.ais.repository;

import com.hexaware.ais.entity.Officer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*
 * @Author: Kishlay Kumar
 * Class: Repository for Officer entity.
 * Description: This interface is used to interact with the Officer entity in the database.
 */
@Repository
public interface OfficerRepository extends JpaRepository<Officer, String> {

    // [SELECT * FROM officer WHERE email = ?]
    Officer findByEmail(String email);
}