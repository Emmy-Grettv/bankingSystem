package com.example.bankingsystem.service;

import com.example.bankingsystem.model.Customer;
import com.example.bankingsystem.repository.CustomerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder){
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer register(Customer customer){
        if(customerRepository.findByEmail(customer.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists!");
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save((customer));
    }

    public Customer login(String email, String rawPassword){
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email Bot Found!"));

        if(!passwordEncoder.matches(rawPassword, customer.getPassword())) {
            throw new RuntimeException(("Invalid Password!"));
        }
        return customer;
    }


}
