package com.example.auth.service;

import com.example.auth.models.entity.EmailVerificationToken;
import com.example.auth.models.entity.User;

public interface EmailVerificationService {

    int setConfirmedAt(String token);
    EmailVerificationToken createToken(User user);
    EmailVerificationToken getByToken(String token);
    void deleteToken(EmailVerificationToken verificationToken);
}
