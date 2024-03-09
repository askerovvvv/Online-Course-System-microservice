package com.example.auth.service.impl;

import com.example.auth.config.JwtService;
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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    @Mock
    private EmailVerificationRepository verificationRepository;
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
                verificationRepository,
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
        // given

        RegisterRequest registerData = new RegisterRequest();
        registerData.setFirstname("test name");
        registerData.setEmail("test@example.com");

        Role mockRole = new Role();

        User mockUser = new User();
        mockUser.setFirstname("test name");
        mockUser.setEmail("test@example.com");
        mockUser.setEmailVerified(false);
        mockUser.setRole(new HashSet<>(List.of(mockRole)));
        EmailVerificationToken mockVerificationToken = new EmailVerificationToken();

        // Mock actions
        when(roleService.getRoleByName(anyString())).thenReturn(mockRole);
        when(userRepository.save(any(User.class))).thenReturn(mockUser);
        when(verificationService.createToken(any(User.class))).thenReturn(mockVerificationToken);
        when(emailSender.buildEmail(anyString(), anyString())).thenReturn("someValue");

        // when
        String actualResponse = mockAuthService.authRegister(registerData);

        // then
        verify(userRepository, times(1)).save(mockUser);
        verify(emailSender).send(eq("test@example.com"), anyString());
        assertEquals("Registered", actualResponse);
    }

    @Test
    void testAuthenticate_Success() {
        // given
        AuthenticationRequest authenticateData = new AuthenticationRequest();
        authenticateData.setEmail("test@example.com");
        authenticateData.setPassword("password");

        User mockUser = new User();
        mockUser.setFirstname("test name");

        // mock actions
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(mockUser));
        when(jwtService.generateToken(mockUser)).thenReturn("Generated token");

        // when
        AuthenticationResponse actualResponse = mockAuthService.authenticate(authenticateData);

        // then
        assertEquals("Generated token", actualResponse.getToken());
    }

}