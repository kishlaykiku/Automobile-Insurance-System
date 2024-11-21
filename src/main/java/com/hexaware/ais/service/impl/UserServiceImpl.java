package com.hexaware.ais.service.impl;

import java.util.List;
import java.util.Optional;

import com.hexaware.ais.entity.User;
import com.hexaware.ais.repository.UserRepository;
import com.hexaware.ais.service.IUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    /******************************************* Methods *******************************************/

    @Override
    public User createUser(User user) {

        logger.info("Creating user with email: {}", user.getEmail());

        return userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) {

        logger.debug("Fetching user with ID: {}", userId);

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            logger.error("User with ID {} not found", userId);
        }

        return user.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    @Override
    public List<User> getAllUsers() {

        logger.info("Fetching all users");

        return userRepository.findAll();
    }

    @Override
    public User updateUser(String userId, User updatedUser) {

        logger.info("Updating user with ID: {}", userId);

        User existingUser = getUserById(userId);

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

        logger.info("Deleting user with ID: {}", userId);

        User existingUser = getUserById(userId);

        userRepository.delete(existingUser);
    }

    @Override
    public User findByEmail(String email) {

        logger.debug("Fetching user with email: {}", email);

        return userRepository.findByEmail(email);
    }
}