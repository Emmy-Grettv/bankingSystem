package com.example.bankingsystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String FirstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;
    private String mobile;
    private LocalDate dob;

    @Column(unique = true)
    private String account;

    @Column(nullable = false)
    private Double balance = 0.0;

    private LocalDateTime lastUpdatedTime;

    @PrePersist
    public void onCreate() {
        this.lastUpdatedTime = LocalDateTime.now();
        this.account = generateAccountNumber();
    }

    @PreUpdate
    public void onUpdate() {
        this.lastUpdatedTime = LocalDateTime.now();
    }

    private String generateAccountNumber(){
        return "ACC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }


    public Customer() {};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public LocalDateTime getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

}


