package com.example.auth.service.impl;

import com.example.auth.config.JwtService;
import com.example.auth.exceptions.ConflictException;
import com.example.auth.exceptions.NoLongerExistsException;
import com.example.auth.models.dto.AuthenticationRequest;
import com.example.auth.models.dto.AuthenticationResponse;
import com.example.auth.models.dto.RegisterRequest;
import com.example.auth.models.entity.EmailVerificationToken;
import com.example.auth.models.entity.Role;
import com.example.auth.models.entity.User;
import com.example.auth.repository.EmailVerificationRepository;
import com.example.auth.repository.UserRepository;
import com.example.auth.service.EmailSender;
import com.example.auth.service.EmailVerificationService;
import com.example.auth.service.RoleService;
import com.example.auth.validator.AuthValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleService roleService;
    @Mock
    private EmailVerificationService verificationService;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthValidator<RegisterRequest> registerValidator;
    @Mock
    private EmailSender emailSender;

    private AuthServiceImpl mockAuthService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockAuthService = new AuthServiceImpl(
                userRepository,
                roleService,
                verificationService,
                jwtService,
                emailSender,
                registerValidator,
                passwordEncoder,
                authenticationManager
        );
    }

    @Test
    void testRegister_Success() {
        // Given
        RegisterRequest registerData = RegisterRequest.builder()
                .firstname("test name")
                .email("test@example.com")
                .build();

        Role mockRole = new Role();
        EmailVerificationToken mockVerificationToken = new EmailVerificationToken();

        User mockUser = User.builder()
                .firstname("test name")
                .email("test@example.com")
                .emailVerified(false)
                .role(new HashSet<>(List.of(mockRole)))
                .build();


        // Mock actions
        when(roleService.getRoleByName(anyString())).thenReturn(mockRole);
        when(userRepository.save(any(User.class))).thenReturn(mockUser);
        when(verificationService.createToken(any(User.class))).thenReturn(mockVerificationToken);
        when(emailSender.buildEmail(anyString(), anyString())).thenReturn("someValue");

        // When
        String actualResponse = mockAuthService.authRegister(registerData);

        // Then
        verify(userRepository, times(1)).save(mockUser);
        verify(emailSender).send(eq("test@example.com"), anyString());
        assertEquals("Registered", actualResponse);
    }

    @Test
    void testAuthenticate_Success() {
        // Given
        AuthenticationRequest authenticateData = new AuthenticationRequest();
        authenticateData.setEmail("test@example.com");
        authenticateData.setPassword("password");

        User mockUser = new User();
        mockUser.setFirstname("test name");

        // Mock actions
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(mockUser));
        when(jwtService.generateToken(mockUser)).thenReturn("Generated token");

        // When
        AuthenticationResponse actualResponse = mockAuthService.authenticate(authenticateData);

        // Then
        assertEquals("Generated token", actualResponse.getToken());
    }

    @Test
    void testConfirmAccount_Success() {
        // Given
        String token = "jwt token";
        User mockUser = new User();

        EmailVerificationToken mockVerificationToken = EmailVerificationToken.builder()
                .user(mockUser)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(20))
                .token("jwt token")
                .build();

        // Mock actions
        when(verificationService.getByToken(token)).thenReturn(mockVerificationToken);
        when(verificationService.setConfirmedAt(token)).thenReturn(1);
        when(userRepository.updateUserEmailVerified(any())).thenReturn(1);

        // When
        String actualResponse = mockAuthService.confirmAccount(token);

        // Then
        assertEquals("Your account has been successfully verified!", actualResponse);
        verify(verificationService, times(1)).setConfirmedAt(token);
        verify(userRepository, times(1)).updateUserEmailVerified(any());
        verify(userRepository, never()).delete(any());
        verify(verificationService, never()).deleteToken(any());
    }

    @Test
    void testConfirmAccount_AlreadyConfirmed() {
        // Given
        String token = "jwt token";
        User mockUser = new User();

        EmailVerificationToken mockVerificationToken = EmailVerificationToken.builder()
                .user(mockUser)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(20))
                .confirmedAt(LocalDateTime.now().plusMinutes(10))
                .token("jwt token")
                .build();

        // Mock actions
        when(verificationService.getByToken(token)).thenReturn(mockVerificationToken);

        // Then
        assertThrows(ConflictException.class, () -> mockAuthService.confirmAccount(token));

        verify(verificationService, never()).setConfirmedAt(token);
        verify(userRepository, never()).updateUserEmailVerified(any());
        verify(userRepository, never()).delete(any());
        verify(verificationService, never()).deleteToken(any());
    }

    @Test
    void testConfirmAccount_TokenExpired() {
        // Given
        String token = "jwt token";
        User mockUser = new User();

        EmailVerificationToken mockVerificationToken = EmailVerificationToken.builder()
                .user(mockUser)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().minusMinutes(20))
                .token("jwt token")
                .build();

        // Mock actions
        when(verificationService.getByToken(token)).thenReturn(mockVerificationToken);

        // Then
        assertThrows(NoLongerExistsException.class, () -> mockAuthService.confirmAccount(token));

        verify(verificationService, never()).setConfirmedAt(token);
        verify(userRepository, never()).updateUserEmailVerified(any());
        verify(userRepository, times(1)).delete(any());
        verify(verificationService, times(1)).deleteToken(any());
    }

}