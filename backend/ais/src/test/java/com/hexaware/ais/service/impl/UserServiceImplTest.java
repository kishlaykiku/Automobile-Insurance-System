package com.hexaware.ais.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hexaware.ais.dto.UserDTO;
import com.hexaware.ais.entity.User;
import com.hexaware.ais.exception.BadRequestException;
import com.hexaware.ais.exception.ResourceNotFoundException;
import com.hexaware.ais.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        // Initialize User and UserDTO objects
        user = new User();
        user.setUserId("6e06f77a-1ea5-4bd2-9e8b-a77a000b060d");
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");
        user.setAddress("123 Main St");
        user.setDob(LocalDate.of(1990, 1, 1));
        user.setAadharNo("111456789012");
        user.setPanNo("ABCDE1214X");

        userDTO = new UserDTO(user);
    }

    @Test
    void testCreateUser_Success() {

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(null);
        when(passwordEncoder.encode(userDTO.getPassword())).thenReturn("encryptedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO createdUser = userService.createUser(userDTO);

        assertNotNull(createdUser);
        assertEquals(user.getUserId(), createdUser.getUserId());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testCreateUser_UserAlreadyExists() {

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(user);

        assertThrows(BadRequestException.class, () -> userService.createUser(userDTO));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testGetUserById_Success() {

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        UserDTO fetchedUser = userService.getUserById(user.getUserId());

        assertNotNull(fetchedUser);
        assertEquals(user.getUserId(), fetchedUser.getUserId());
        verify(userRepository, times(1)).findById(user.getUserId());
    }

    @Test
    void testGetUserById_NotFound() {

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(user.getUserId()));
        verify(userRepository, times(1)).findById(user.getUserId());
    }

    @Test
    void testGetAllUsers_Success() {

        List<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.findAll()).thenReturn(users);

        List<UserDTO> userList = userService.getAllUsers();

        assertNotNull(userList);
        assertFalse(userList.isEmpty());
        assertEquals(1, userList.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetAllUsers_Empty() {

        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        List<UserDTO> userList = userService.getAllUsers();

        assertNotNull(userList);
        assertTrue(userList.isEmpty());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testUpdateUser_Success() {

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO updatedUser = userService.updateUser(user.getUserId(), userDTO);

        assertNotNull(updatedUser);
        assertEquals(user.getUserId(), updatedUser.getUserId());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateUser_NotFound() {

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(user.getUserId(), userDTO));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testDeleteUser_Success() {

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        userService.deleteUser(user.getUserId());

        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testDeleteUser_NotFound() {

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(user.getUserId()));
        verify(userRepository, never()).delete(any(User.class));
    }

    @Test
    void testFindByEmail_Success() {

        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);

        UserDTO fetchedUser = userService.findByEmail(user.getEmail());

        assertNotNull(fetchedUser);
        assertEquals(user.getEmail(), fetchedUser.getEmail());
        verify(userRepository, times(1)).findByEmail(user.getEmail());
    }

    @Test
    void testFindByEmail_NotFound() {

        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> userService.findByEmail(user.getEmail()));
        verify(userRepository, times(1)).findByEmail(user.getEmail());
    }
}