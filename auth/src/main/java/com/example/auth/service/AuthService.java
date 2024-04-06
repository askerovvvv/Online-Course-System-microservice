package com.example.auth.service;

import com.example.auth.models.requestsDto.AuthenticationRequest;
import com.example.auth.models.responsesDto.AuthenticationResponse;
import com.example.auth.models.requestsDto.RegisterRequest;
import org.springframework.web.multipart.MultipartFile;

public interface AuthService {

    String authRegister(RegisterRequest registerData, MultipartFile userImageFile);
    String confirmAccount(String token);
    AuthenticationResponse authenticate(AuthenticationRequest authenticateData);

}
