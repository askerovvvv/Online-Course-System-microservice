package com.example.auth.service;

import com.example.auth.models.dto.AuthenticationRequest;
import com.example.auth.models.dto.AuthenticationResponse;
import com.example.auth.models.dto.RegisterRequest;

public interface AuthService {

    String authRegister(RegisterRequest registerData);
    String confirmAccount(String token);
    AuthenticationResponse authenticate(AuthenticationRequest requestData);
}
