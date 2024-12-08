package com.hexaware.ais.service;

import java.util.List;

import com.hexaware.ais.dto.UserDTO;


/*
 * @Author: Kishlay Kumar
 * Interface: IUserService
 * Description: This interface defines the contract for user-related operations
 */
public interface IUserService {

    /******************************************* Method Signatures *******************************************/

    // Create a new user
    UserDTO createUser(UserDTO userDTO);

    // Get a user by ID
    UserDTO getUserById(String userId);

    // Get all users
    List<UserDTO> getAllUsers();

    // Update a user
    UserDTO updateUser(String userId, UserDTO userDTO);

    // Delete a user
    void deleteUser(String userId);

    // Find a user by email
    UserDTO findByEmail(String email);
}