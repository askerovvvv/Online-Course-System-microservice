package com.example.auth.service;

public interface EmailVerificationService {

    int setConfirmedAt(String token);
}
