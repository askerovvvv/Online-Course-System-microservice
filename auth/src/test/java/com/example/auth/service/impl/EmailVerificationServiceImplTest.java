package com.example.auth.service.impl;

import com.example.auth.models.entity.EmailVerificationToken;
import com.example.auth.models.entity.User;
import com.example.auth.repository.EmailVerificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class EmailVerificationServiceImplTest {

    @Mock
    private EmailVerificationRepository verificationRepository;

    private EmailVerificationServiceImpl verificationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        verificationService = new EmailVerificationServiceImpl(verificationRepository);
    }

    @Test
    void testSetConfirmedAt() {
        // Mock actions
        when(verificationRepository.updateConfirmedAt(any(),any())).thenReturn(1);

        // When
        int changes = verificationService.setConfirmedAt("token");

        //Then
        assertEquals(1, changes);
    }

    @Test
    void testCreateToken() {
        // Given
        User mockUser = new User();
        EmailVerificationToken mockVerificationToken = new EmailVerificationToken();

        // Mock actions
        when(verificationRepository.save(any(EmailVerificationToken.class))).thenReturn(mockVerificationToken);

        // When
        EmailVerificationToken actualResult = verificationService.createToken(mockUser);

        // Then
        verify(verificationRepository, times(1)).save(any(EmailVerificationToken.class));
    }
}