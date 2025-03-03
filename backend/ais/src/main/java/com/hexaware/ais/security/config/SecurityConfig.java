package com.hexaware.ais.security.config;

import com.hexaware.ais.security.filter.JwtAuthenticationFilter;
import com.hexaware.ais.security.handler.CustomAccessDeniedHandler;
import com.hexaware.ais.security.handler.CustomAuthenticationEntryPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


/*
 * @Author: Kishlay Kumar
 * Class: SecurityConfig
 * Description: This class configures the security settings for the application
 */
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true); // Allow credentials (Authorization header)
        config.addAllowedOrigin("http://localhost:4200"); // http://localhost:4200 http://insurance-system-angular.s3-website.eu-north-1.amazonaws.com/
        config.addAllowedHeader("*"); // Allow all headers
        config.addAllowedMethod("*"); // Allow all HTTP methods (GET, POST, etc.)
        config.addExposedHeader("Authorization"); // Expose Authorization header to the client

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    // @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(request -> {

                CorsConfiguration config = new CorsConfiguration();

                config.setAllowCredentials(true);
                config.addAllowedOrigin("http://localhost:4200"); // http://localhost:4200 http://insurance-system-angular.s3-website.eu-north-1.amazonaws.com/
                config.addAllowedHeader("*");
                config.addAllowedMethod("*");
                config.addExposedHeader("Authorization");

                return config;
            }))
            .authorizeHttpRequests(requests -> requests
            // Public authentication endpoints
            .requestMatchers("/api/auth/**", "/api/users/create", "/api/policies/get/statistics/active-policy", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**").permitAll() // Public authentication endpoints
            // Users controller rule
            .requestMatchers("/api/users/**").hasAnyRole("USER", "OFFICER")
            // Officer controller rule
            .requestMatchers("/api/officer/**").hasRole("OFFICER")
            // Policy controller rule
            .requestMatchers("/api/policies/**").hasAnyRole("USER", "OFFICER")
            // Proposal controller rule
            .requestMatchers("/api/proposals/**").hasAnyRole("USER", "OFFICER")
            // Claim controller rule
            .requestMatchers("/api/claims/**").hasAnyRole("USER", "OFFICER")
            // Payment controller rule
            .requestMatchers("/api/payments/**").hasAnyRole("USER", "OFFICER")
            // Vehicle controller rule
            .requestMatchers("/api/vehicles/**").hasAnyRole("USER", "OFFICER")
            .anyRequest().authenticated())
            .exceptionHandling(handling -> handling
            .accessDeniedHandler(new CustomAccessDeniedHandler()) // Custom 403
            .authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
    }
}