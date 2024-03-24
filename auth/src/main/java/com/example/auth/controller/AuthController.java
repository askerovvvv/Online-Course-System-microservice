package com.example.auth.controller;

import com.example.auth.models.dto.AuthenticationRequest;
import com.example.auth.models.dto.RegisterRequest;
import com.example.auth.service.AuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam("photo") MultipartFile userImage,
            @RequestParam("user") String userJsonData
    ) {
        RegisterRequest userData;
        try {
            userData = new ObjectMapper().readValue(userJsonData, RegisterRequest.class);
        } catch (JsonProcessingException e)  {
            throw new RuntimeException(e); // TODO: change
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authService.authRegister(userData, userImage));
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> activate(@RequestParam("token") String token) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.confirmAccount(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.authenticate(request));
    }
}
