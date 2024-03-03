package com.example.auth.repository;

import com.example.auth.models.entity.EmailVerificationToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface EmailVerificationRepository extends JpaRepository<EmailVerificationToken, Long> {
    Optional<EmailVerificationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE EmailVerificationToken e " +
    "SET e.confirmedAt = ?1 " +
    "WHERE e.token = ?2")
    int updateConfirmedAt(LocalDateTime confirmedTime, String string);
}
