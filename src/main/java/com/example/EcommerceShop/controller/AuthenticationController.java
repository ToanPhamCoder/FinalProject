package com.example.EcommerceShop.controller;

import com.example.EcommerceShop.entity.Customer;
import com.example.EcommerceShop.request.AuthenticationRequest;
import com.example.EcommerceShop.security.JwtUtil;
import com.example.EcommerceShop.service.AuthenticationService;
import com.example.EcommerceShop.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

@RestController
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService customerService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public String createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Customer user) {
        if (customerService.register(user)) {
            return ResponseEntity.ok(Map.of("status", "success"));
        }
        return ResponseEntity.status(400).body(Map.of("status", "error", "message", "Username already exists"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return customerService.login(authenticationRequest.getEmail(), authenticationRequest.getPassword())
                .map(customer -> {
                    String token = jwtUtil.generateToken(customer.getEmail());
                    return ResponseEntity.ok(Map.of("status", "success", "token", token));
                })
                .orElse(ResponseEntity.status(400).body(Map.of("status", "error", "message", "Invalid credentials")));
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;
            logger.debug("Token received: {}", token);
            String username = jwtUtil.extractUsername(token);
            boolean isValid = jwtUtil.validateToken(token, username);
            logger.debug("Token valid: {}", isValid);
            if (isValid) {
                return ResponseEntity.ok(Map.of("status", "success", "username", username));
            } else {
                return ResponseEntity.status(400).body(Map.of("status", "error", "message", "Invalid token"));
            }
        } catch (Exception e) {
            logger.error("Token validation error: {}", e.getMessage());
            return ResponseEntity.status(400).body(Map.of("status", "error", "message", "Invalid token"));
        }
    }
}