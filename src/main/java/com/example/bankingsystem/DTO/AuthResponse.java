package com.example.bankingsystem.DTO;

import lombok.Getter;

@Getter
public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
