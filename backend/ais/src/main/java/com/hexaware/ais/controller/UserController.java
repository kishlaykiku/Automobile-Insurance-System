package com.hexaware.ais.controller;

import java.util.List;

import com.hexaware.ais.dto.UserDTO;
import com.hexaware.ais.service.IUserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/*
 * @Author: Kishlay Kumar
 * Class: UserController
 * Description: This class handles HTTP requests related to user operations
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    /******************************************* Dependencies *******************************************/

    @Autowired
    private IUserService userService;

    /******************************************* Endpoints *******************************************/

    // Create a new user (Public)
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {

        UserDTO createdUser = userService.createUser(userDTO);

        return ResponseEntity.ok(createdUser);
    }

    // Get a user by ID (User Only)
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String userId) {

        UserDTO user = userService.getUserById(userId);

        return ResponseEntity.ok(user);
    }

    // Get all users (Officer Only)
    @PreAuthorize("hasRole('OFFICER')")
    @GetMapping("/getall")
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        List<UserDTO> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    // Update a user (User Only)
    @PreAuthorize("hasRole('USER') and authentication.name == #userDTO.email")
    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String userId, @Valid @RequestBody UserDTO userDTO) {

        UserDTO updatedUser = userService.updateUser(userId, userDTO);

        return ResponseEntity.ok(updatedUser);
    }

    // Delete a user (User Only)
    @PreAuthorize("hasRole('USER') and @userService.getUserById(#userId).email == principal.username")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {

        userService.deleteUser(userId);

        return ResponseEntity.ok("User deleted successfully");
    }

    // Find a user by email (User Only)
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/get/user-by-email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {

        UserDTO user = userService.findByEmail(email);

        return ResponseEntity.ok(user);
    }
}