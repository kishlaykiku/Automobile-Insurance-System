package com.hexaware.ais.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.hexaware.ais.entity.User;
import com.hexaware.ais.dto.UserDTO;
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
    public UserDTO createUser(UserDTO userDTO) {

        logger.info("Creating user with email: {}", userDTO.getEmail());

        User user = userDTO.toEntity();
        User savedUser = userRepository.save(user);

        return new UserDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(String userId) {

        logger.debug("Fetching user with ID: {}", userId);

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {

            logger.error("User with ID {} not found", userId);

            throw new RuntimeException("User not found with ID: " + userId);
        }

        return new UserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        logger.info("Fetching all users");

        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (User user : users) {

            userDTOList.add(new UserDTO(user));
        }

        return userDTOList;
    }

    @Override
    public UserDTO updateUser(String userId, UserDTO userDTO) {

        logger.info("Updating user with ID: {}", userId);

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setAddress(userDTO.getAddress());
        existingUser.setDob(userDTO.getDob());
        existingUser.setAadharNo(userDTO.getAadharNo());
        existingUser.setPanNo(userDTO.getPanNo());

        User updatedUser = userRepository.save(existingUser);

        return new UserDTO(updatedUser);
    }

    @Override
    public void deleteUser(String userId) {

        logger.info("Deleting user with ID: {}", userId);

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        userRepository.delete(existingUser);
    }

    @Override
    public UserDTO findByEmail(String email) {

        logger.debug("Fetching user with email: {}", email);

        User user = userRepository.findByEmail(email);

        if (user == null) {

            throw new RuntimeException("User not found with email: " + email);
        }

        return new UserDTO(user);
    }
}