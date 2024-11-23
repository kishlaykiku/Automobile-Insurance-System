package com.hexaware.ais.controller;

import java.util.List;

import com.hexaware.ais.entity.User;
import com.hexaware.ais.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // Create a new user
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User createdUser = userService.createUser(user);

        return ResponseEntity.ok(createdUser);
    }

    // Get a user by ID
    @GetMapping("/get/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {

        User user = userService.getUserById(userId);

        return ResponseEntity.ok(user);
    }

    // Get all users
    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> users = userService.getAllUsers();

        return ResponseEntity.ok(users);
    }

    // Update a user
    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) {

        User updatedUser = userService.updateUser(userId, user);

        return ResponseEntity.ok(updatedUser);
    }

    // Delete a user
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {

        userService.deleteUser(userId);

        return ResponseEntity.ok("User deleted successfully");
    }

    // Find a user by email
    @GetMapping("/get/user-by-email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {

        User user = userService.findByEmail(email);

        return ResponseEntity.ok(user);
    }
}