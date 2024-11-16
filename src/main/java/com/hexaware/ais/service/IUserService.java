package com.hexaware.ais.service;

import java.util.List;

import com.hexaware.ais.entity.User;


/*
 * @Author: Kishlay Kumar
 * Interface: IUserService
 * Description: This interface defines the contract for user-related operations
 */
public interface IUserService {

    /******************************************* Method Signatures *******************************************/

    // Create a new user
    User createUser(User user);

    // Get a user by ID
    User getUserById(String userId);

    // Get all users
    List<User> getAllUsers();

    // Update a user
    User updateUser(String userId, User user);

    // Delete a user
    void deleteUser(String userId);

    // Find a user by email
    User findByEmail(String email);
}