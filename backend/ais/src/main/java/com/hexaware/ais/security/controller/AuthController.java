package com.hexaware.ais.security.controller;

import com.hexaware.ais.security.dto.AuthRequest;
import com.hexaware.ais.security.dto.AuthResponse;
import com.hexaware.ais.security.service.CustomUserDetailsService;
import com.hexaware.ais.security.util.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/*
 * @Author: Kishlay Kumar
 * Class: AuthController
 * Description: Controller for authentication-related operations such as login and token refresh.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private final AuthenticationManager authenticationManager;
    
    @Autowired
    private final JwtUtil jwtUtil;

    @Autowired
    private final CustomUserDetailsService customUserDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {

        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    // Login endpoint to authenticate user credentials and issue a JWT token
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest) {

        try {

            // Authenticate user credentials
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

            // Fetch user role
            String role = customUserDetailsService.getRoleByUsername(authRequest.getUsername());

            // Fetch user name
            String name = customUserDetailsService.getNameByUsername(authRequest.getUsername());

            // Fetch user ID
            String userId = customUserDetailsService.getUserIdByUsername(authRequest.getUsername());

            // Generate JWT token
            String jwtToken = jwtUtil.generateToken(authRequest.getUsername(), role, name, userId);

            logger.info("User {} logged in successfully", authRequest.getUsername());

            return ResponseEntity.ok(new AuthResponse(jwtToken));

        }
        catch (BadCredentialsException e) {

            logger.error("Invalid credentials for user: {}", authRequest.getUsername());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    // Refresh endpoint to generate a new JWT token using the old token
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestHeader("Authorization") String authorizationHeader) {

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Authorization header format");
        }

        String oldToken = authorizationHeader.substring(7);

        try {

            // Generate refreshed token
            String refreshedToken = jwtUtil.refreshToken(oldToken);

            logger.info("Token refreshed successfully");

            return ResponseEntity.ok(new AuthResponse(refreshedToken));

        }
        catch (Exception e) {

            logger.error("Failed to refresh token: {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
    }

    // Logout endpoint to clear the SecurityContext for the current user
    @PostMapping("/logout")
    public ResponseEntity<String> logout() {

        // Clear the SecurityContext for the current user
        SecurityContextHolder.clearContext();

        return ResponseEntity.ok("Logged out successfully.");
    }
}