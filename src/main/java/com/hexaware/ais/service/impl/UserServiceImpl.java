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

        logger.debug("[START] Creating user with email: {}", userDTO.getEmail());

        User user = userDTO.toEntity();
        User savedUser = userRepository.save(user);

        logger.debug("[END] User created successfully with ID: {}", savedUser.getUserId());

        return new UserDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(String userId) {

        logger.debug("[START] Fetching user with ID: {}", userId);

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {

            logger.error("[END] User with ID ({}) not found", userId);

            throw new RuntimeException("User not found with ID: " + userId);
        }

        logger.debug("[END] User with ID ({}) fetched successfully", userId);

        return new UserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {

        logger.debug("[START] Fetching all users");

        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        if(users.isEmpty()) {

            logger.warn("[END] No users found int the system");
        }
        else {

            for (User user : users) {

                userDTOList.add(new UserDTO(user));
            }

            logger.debug("[END] Fetched all users successfully");
        }

        return userDTOList;
    }

    @Override
    public UserDTO updateUser(String userId, UserDTO userDTO) {

        logger.debug("[START] Updating user with ID: {}", userId);

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> {

                    logger.error("[END] User with ID ({}) not found", userId);
                    return new RuntimeException("User not found with ID: " + userId);
                }
            );

        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setAddress(userDTO.getAddress());
        existingUser.setDob(userDTO.getDob());
        existingUser.setAadharNo(userDTO.getAadharNo());
        existingUser.setPanNo(userDTO.getPanNo());

        User updatedUser = userRepository.save(existingUser);

        logger.debug("[END] User with ID ({}) updated successfully", userId);
        
        return new UserDTO(updatedUser);
    }

    @Override
    public void deleteUser(String userId) {

        logger.debug("[START] Deleting user with ID: {}", userId);

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> {

                    logger.error("[END] User with ID ({}) not found", userId);
                    return new RuntimeException("User not found with ID: " + userId);
                }
            );

        userRepository.delete(existingUser);

        logger.debug("[END] User with ID ({}) deleted successfully", userId);
    }

    @Override
    public UserDTO findByEmail(String email) {

        logger.debug("[START] Fetching user with email: {}", email);

        User user = userRepository.findByEmail(email);

        if (user == null) {

            logger.error("No user found with email: {}", email);
            throw new RuntimeException("User not found with email: " + email);
        }

        logger.debug("[END] User with email ({}) fetched successfully", email);

        return new UserDTO(user);
    }
}