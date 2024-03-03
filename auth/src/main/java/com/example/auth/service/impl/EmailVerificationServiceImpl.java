package com.example.auth.service.impl;

import com.example.auth.exceptions.ConflictException;
import com.example.auth.exceptions.NoLongerExistsException;
import com.example.auth.exceptions.NotFoundException;
import com.example.auth.models.entity.EmailVerificationToken;
import com.example.auth.repository.EmailVerificationRepository;
import com.example.auth.repository.UserRepository;
import com.example.auth.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class EmailVerificationServiceImpl implements EmailVerificationService {

    private final EmailVerificationRepository verificationRepository;
    private final UserRepository userRepository;


    @Override
    public int setConfirmedAt(String token) {
        return verificationRepository.updateConfirmedAt(LocalDateTime.now(), token);
    }
}

