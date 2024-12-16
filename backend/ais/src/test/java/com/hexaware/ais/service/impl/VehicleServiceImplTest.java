package com.hexaware.ais.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.hexaware.ais.dto.VehicleDTO;
import com.hexaware.ais.entity.User;
import com.hexaware.ais.entity.Vehicle;
import com.hexaware.ais.exception.ResourceNotFoundException;
import com.hexaware.ais.repository.UserRepository;
import com.hexaware.ais.repository.VehicleRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class VehicleServiceImplTest {

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private UserRepository userRepository;

    private Vehicle vehicle;
    private VehicleDTO vehicleDTO;
    private User user;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        // Initialize User
        user = new User();
        user.setUserId("6e06f77a-1ea5-4bd2-9e8b-a77a000b060d");
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");

        // Initialize Vehicle
        vehicle = new Vehicle();
        vehicle.setVehicleId("vehicle123");
        vehicle.setType("Car");
        vehicle.setModel("Toyota Corolla");
        vehicle.setRegistrationNo("ABC123");
        vehicle.setYear(2020);
        vehicle.setUser(user);

        // Initialize VehicleDTO
        vehicleDTO = new VehicleDTO(vehicle);
    }

    @Test
    void testCreateVehicle_Success() {

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        VehicleDTO createdVehicle = vehicleService.createVehicle(vehicleDTO);

        assertNotNull(createdVehicle);
        assertEquals(vehicle.getVehicleId(), createdVehicle.getVehicleId());
        verify(vehicleRepository, times(1)).save(any(Vehicle.class));
    }

    @Test
    void testCreateVehicle_UserNotFound() {

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> vehicleService.createVehicle(vehicleDTO));
        verify(vehicleRepository, never()).save(any(Vehicle.class));
    }

    @Test
    void testGetVehicleById_Success() {
    
        when(vehicleRepository.findById(vehicle.getVehicleId())).thenReturn(Optional.of(vehicle));

        VehicleDTO fetchedVehicle = vehicleService.getVehicleById(vehicle.getVehicleId());

        assertNotNull(fetchedVehicle);
        assertEquals(vehicle.getVehicleId(), fetchedVehicle.getVehicleId());
        verify(vehicleRepository, times(1)).findById(vehicle.getVehicleId());
    }

    @Test
    void testGetVehicleById_NotFound() {

        when(vehicleRepository.findById(vehicle.getVehicleId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> vehicleService.getVehicleById(vehicle.getVehicleId()));
        verify(vehicleRepository, times(1)).findById(vehicle.getVehicleId());
    }

    @Test
    void testGetAllVehicles_Success() {
    
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle);
        when(vehicleRepository.findAll()).thenReturn(vehicles);

        List<VehicleDTO> vehicleList = vehicleService.getAllVehicles();

        assertNotNull(vehicleList);
        assertFalse(vehicleList.isEmpty());
        assertEquals(1, vehicleList.size());
        verify(vehicleRepository, times(1)).findAll();
    }

    @Test
    void testGetAllVehicles_Empty() {

        when(vehicleRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(ResourceNotFoundException.class, () -> vehicleService.getAllVehicles());
        verify(vehicleRepository, times(1)).findAll();
    }

    @Test
    void testGetVehiclesByUserId_Success() {

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(vehicle);
        when(vehicleRepository.findByUserUserId(user.getUserId())).thenReturn(vehicles);

        List<VehicleDTO> vehicleList = vehicleService.getVehiclesByUserId(user.getUserId());

        assertNotNull(vehicleList);
        assertFalse(vehicleList.isEmpty());
        assertEquals(1, vehicleList.size());
        verify(vehicleRepository, times(1)).findByUserUserId(user.getUserId());
    }

    @Test
    void testGetVehiclesByUserId_NotFound() {

        when(vehicleRepository.findByUserUserId(user.getUserId())).thenReturn(new ArrayList<>());

        assertThrows(ResourceNotFoundException.class, () -> vehicleService.getVehiclesByUserId(user.getUserId()));
        verify(vehicleRepository, times(1)).findByUserUserId(user.getUserId());
    }

    @Test
    void testUpdateVehicle_Success() {

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));

        when(vehicleRepository.findById(vehicle.getVehicleId())).thenReturn(Optional.of(vehicle));

        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);

        VehicleDTO updatedVehicle = vehicleService.updateVehicle(vehicle.getVehicleId(), vehicleDTO);

        assertNotNull(updatedVehicle);
        assertEquals(vehicle.getVehicleId(), updatedVehicle.getVehicleId());
        verify(vehicleRepository, times(1)).save(any(Vehicle.class));
        verify(userRepository, times(1)).findById(user.getUserId());
    }

    @Test
    void testUpdateVehicle_NotFound() {

        when(vehicleRepository.findById(vehicle.getVehicleId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> vehicleService.updateVehicle(vehicle.getVehicleId(), vehicleDTO));
        verify(vehicleRepository, never()).save(any(Vehicle.class));
    }

    @Test
    void testDeleteVehicle_Success() {

        when(vehicleRepository.findById(vehicle.getVehicleId())).thenReturn(Optional.of(vehicle));
        doNothing().when(vehicleRepository).delete(vehicle);

        vehicleService.deleteVehicle(vehicle.getVehicleId());

        verify(vehicleRepository, times(1)).delete(vehicle);
    }

    @Test
    void testDeleteVehicle_NotFound() {

        when(vehicleRepository.findById(vehicle.getVehicleId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> vehicleService.deleteVehicle(vehicle.getVehicleId()));
        verify(vehicleRepository, never()).delete(any(Vehicle.class));
    }
}