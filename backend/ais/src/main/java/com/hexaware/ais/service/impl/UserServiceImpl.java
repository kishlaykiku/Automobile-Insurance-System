package com.hexaware.ais.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.hexaware.ais.entity.User;
import com.hexaware.ais.dto.UserDTO;
import com.hexaware.ais.repository.UserRepository;
import com.hexaware.ais.service.IUserService;
import com.hexaware.ais.exception.BadRequestException;
import com.hexaware.ais.exception.InvalidArgumentException;
import com.hexaware.ais.exception.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/*
 * @Author: Kishlay Kumar
 * Class: UserServiceImpl
 * Description: This class implements the IUserService interface for user-related operations
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    /******************************************* Dependencies *******************************************/

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /******************************************* Methods *******************************************/

    // Create a new user
    @Override
    public UserDTO createUser(UserDTO userDTO) {

        if(userDTO == null) {

            logger.error("UserDTO is null");
            throw new InvalidArgumentException("User data is required.");
        }

        logger.debug("[START] Creating user with email: {}", userDTO.getEmail());

        if (userRepository.findByEmail(userDTO.getEmail()) != null) {

            logger.error("[END] User with email ({}) already exists", userDTO.getEmail());
            throw new BadRequestException("User already exists with this email.");
        }

        // Encrypt the password
        String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encryptedPassword);

        User user = userDTO.toEntity();
        User savedUser = userRepository.save(user);

        logger.debug("[END] User created successfully with ID: {}", savedUser.getUserId());

        return new UserDTO(savedUser);
    }

    // Fetch a user by ID
    @Override
    public UserDTO getUserById(String userId) {

        if (userId == null || userId.isBlank()) {

            logger.error("User ID cannot be null or empty");
            throw new BadRequestException("User ID is required.");
        }

        logger.debug("[START] Fetching user with ID: {}", userId);

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {

            logger.error("[END] User with ID ({}) not found", userId);
            throw new ResourceNotFoundException("User not found with ID: " + userId);
        }

        logger.debug("[END] User with ID ({}) fetched successfully", userId);

        return new UserDTO(user);
    }

    // Fetch all users
    @Override
    public List<UserDTO> getAllUsers() {

        logger.debug("[START] Fetching all users");

        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();

        if(users.isEmpty()) {

            logger.warn("[END] No users found in the system");
            return new ArrayList<>();
        }
        else {

            for (User user : users) {

                userDTOList.add(new UserDTO(user));
            }

            logger.debug("[END] Fetched all users successfully");
        }

        return userDTOList;
    }

    // Update a user
    @Override
    public UserDTO updateUser(String userId, UserDTO userDTO) {

        logger.debug("[START] Updating user with ID: {}", userId);

        if(userDTO == null) {

            logger.error("[END] UserDTO is null");
            throw new InvalidArgumentException("User data is required.");
        }

        if (userId == null || userId.isBlank()) {

            logger.error("[END] User ID cannot be null or empty");
            throw new BadRequestException("User ID is required.");
        }

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> {

                    logger.error("[END] User with ID ({}) not found", userId);
                    return new ResourceNotFoundException("User not found with ID: " + userId);
                }
            );

        if (!existingUser.getEmail().equals(userDTO.getEmail()) && userRepository.findByEmail(userDTO.getEmail()) != null) {

            logger.error("[END] User with email ({}) already exists", userDTO.getEmail());
            throw new BadRequestException("User already exists with this email.");
        }

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

    // Delete a user
    @Override
    public void deleteUser(String userId) {

        if (userId == null || userId.isBlank()) {

            logger.error("User ID cannot be null or empty");
            throw new BadRequestException("User ID is required.");
        }

        logger.debug("[START] Deleting user with ID: {}", userId);

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> {

                    logger.error("[END] User with ID ({}) not found", userId);
                    return new ResourceNotFoundException("User not found with ID: " + userId);
                }
            );

        userRepository.delete(existingUser);

        logger.debug("[END] User with ID ({}) deleted successfully", userId);
    }

    // Fetch a user by email
    @Override
    public UserDTO findByEmail(String email) {

        if (email == null || email.isBlank()) {

            logger.error("Email cannot be null or empty");
            throw new BadRequestException("Email is required.");
        }

        logger.debug("[START] Fetching user with email: {}", email);

        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {

            logger.error("[END] Invalid email format: {}", email);
            throw new BadRequestException("Invalid email format.");
        }

        User user = userRepository.findByEmail(email);

        if (user == null) {

            logger.error("No user found with email: {}", email);
            throw new ResourceNotFoundException("User not found with email: " + email);
        }

        logger.debug("[END] User with email ({}) fetched successfully", email);

        return new UserDTO(user);
    }
}