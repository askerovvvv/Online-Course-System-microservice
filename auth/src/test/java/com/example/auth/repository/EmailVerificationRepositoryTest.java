package com.example.auth.repository;

import com.example.auth.models.entity.EmailVerificationToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmailVerificationRepositoryTest {

    @Autowired
    EmailVerificationRepository verificationRepository;

    @Test
    void testUpdateConfirmedAt() {
        // Given
        EmailVerificationToken mockVerificationToken = EmailVerificationToken.builder()
                .token("token")
                .createdAt(LocalDateTime.now())
                .build();
        verificationRepository.save(mockVerificationToken);

        // When
        int updateConfirmedAt = verificationRepository.updateConfirmedAt(LocalDateTime.now(), "token");

        // Then
        assertEquals(1, updateConfirmedAt);
    }
}