package com.hexaware.ais.service.impl;

import java.util.List;
import java.util.Optional;

import com.hexaware.ais.entity.User;
import com.hexaware.ais.repository.UserRepository;
import com.hexaware.ais.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * @Author: Kishlay Kumar
 * Class: UserServiceImpl
 * Description: This class implements the IUserService interface for user-related operations
 */
@Service
public class UserServiceImpl implements IUserService {

    /******************************************* Dependencies *******************************************/

    @Autowired
    private UserRepository userRepository;

    /******************************************* Methods *******************************************/

    @Override
    public User createUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) {

        Optional<User> user = userRepository.findById(userId);

        return user.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User updateUser(String userId, User updatedUser) {

        User existingUser = getUserById(userId); // Reuse method to check existence

        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAddress(updatedUser.getAddress());
        existingUser.setDob(updatedUser.getDob());
        existingUser.setAadharNo(updatedUser.getAadharNo());
        existingUser.setPanNo(updatedUser.getPanNo());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(String userId) {

        User existingUser = getUserById(userId);

        userRepository.delete(existingUser);
    }

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email);
    }
}