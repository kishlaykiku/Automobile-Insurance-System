package com.hexaware.ais.repository;

import com.hexaware.ais.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * @Author: Kishlay Kumar
 * Class: Repository for User entity.
 * Description: This interface is used to interact with the User entity in the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);
}