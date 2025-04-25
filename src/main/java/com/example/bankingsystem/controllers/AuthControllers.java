package com.example.bankingsystem.controllers;

import com.example.bankingsystem.DTO.AuthRequest;
import com.example.bankingsystem.DTO.AuthResponse;
import com.example.bankingsystem.model.Customer;
import com.example.bankingsystem.service.CustomerService;
import com.example.bankingsystem.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class AuthControllers {
    CustomerService customerService;
    JwtUtil jwtUtil;

    public AuthControllers(CustomerService customerService, JwtUtil jwtUtil) {
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Customer customer){
        try{
            Customer newCustomer = customerService.register(customer);
            return ResponseEntity.ok(newCustomer);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        try{
            Customer customer = customerService.login(authRequest.getEmail(), authRequest.getPassword());
            String token = jwtUtil.generateToken(customer.getEmail());
            return ResponseEntity.ok(new AuthResponse(token));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
