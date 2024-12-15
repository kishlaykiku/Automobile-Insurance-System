package com.hexaware.ais.security.service;

import java.util.Optional;
import java.util.Collections;

import com.hexaware.ais.entity.User;
import com.hexaware.ais.entity.Officer;
import com.hexaware.ais.repository.OfficerRepository;
import com.hexaware.ais.repository.UserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/*
 * @Author: Kishlay Kumar
 * Class: CustomUserDetailsService
 * Description: Custom UserDetailsService implementation for Spring Security.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final OfficerRepository officerRepository;

    public CustomUserDetailsService(UserRepository userRepository, OfficerRepository officerRepository) {

        this.userRepository = userRepository;
        this.officerRepository = officerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // First, check if the user is in the User table
        User user = userRepository.findByEmail(username);

        if (user != null) {

            return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
                    .password(user.getPassword())
                    .authorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))
                    .build();
        }

        // If not found, check the Officer table
        Optional<Officer> officerOptional = officerRepository.findByEmail(username);

        if (officerOptional.isPresent()) {

            Officer officer = officerOptional.get();

            return org.springframework.security.core.userdetails.User.withUsername(officer.getEmail())
                    .password(officer.getPassword())
                    .authorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_OFFICER")))
                    .build();
        }

        // If not found in either table, throw exception
        throw new UsernameNotFoundException("User not found with email: " + username);
    }

    public String getRoleByUsername(String username) {

        // Fetch from User repository
        User user = userRepository.findByEmail(username);
        if (user != null) {
            return "ROLE_USER";
        }

        // Fetch from Officer repository
        Optional<Officer> officerOptional = officerRepository.findByEmail(username);
        if (officerOptional.isPresent()) {
            return "ROLE_OFFICER";
        }

        // Return null if no role is found
        return null;
    }

    public String getNameByUsername(String username) {

        // Check if user exists in User repository
        User user = userRepository.findByEmail(username);
        if (user != null) {
            return user.getName(); // Assuming User entity has a 'name' field
        }

        // Check if user exists in Officer repository
        Optional<Officer> officerOptional = officerRepository.findByEmail(username);
        if (officerOptional.isPresent()) {
            Officer officer = officerOptional.get();
            return officer.getName(); // Assuming Officer entity has a 'name' field
        }

        // If not found in either repository, return null or throw an exception
        return null;
    }

    public String getUserIdByUsername(String username) {
        User user = userRepository.findByEmail(username);
        if (user != null) {
            return user.getUserId();
        }
        return null;
    }
}