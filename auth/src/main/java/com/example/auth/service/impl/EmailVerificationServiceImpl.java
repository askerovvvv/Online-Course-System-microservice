package com.example.auth.service.impl;

import com.example.auth.exceptions.ConflictException;
import com.example.auth.exceptions.NoLongerExistsException;
import com.example.auth.exceptions.NotFoundException;
import com.example.auth.models.entity.EmailVerificationToken;
import com.example.auth.models.entity.User;
import com.example.auth.repository.EmailVerificationRepository;
import com.example.auth.repository.UserRepository;
import com.example.auth.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class EmailVerificationServiceImpl implements EmailVerificationService {

    private final EmailVerificationRepository verificationRepository;

    @Override
    public int setConfirmedAt(String token) {
        return verificationRepository.updateConfirmedAt(LocalDateTime.now(), token);
    }

    @Override
    public EmailVerificationToken createToken(User user) {
        String token = UUID.randomUUID().toString();

        EmailVerificationToken emailVerificationToken = EmailVerificationToken.builder()
                .token(token)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(20))
                .user(user)
                .build();
        verificationRepository.save(emailVerificationToken);

        return emailVerificationToken;
    }

    @Override
    public EmailVerificationToken getByToken(String token) {
        return verificationRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundException("Account not found!"));
    }

    @Override
    public void deleteToken(EmailVerificationToken verificationToken) {
        verificationRepository.delete(verificationToken);
    }
}

