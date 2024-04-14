package com.example.auth.validator;

import com.example.auth.exceptions.CustomBadRequestException;
import com.example.auth.exceptions.DefaultValidationException;
import com.example.auth.models.requestsDto.AuthenticationRequest;
import com.example.auth.models.entity.User;
import com.example.auth.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthValidatorTest {

    @Mock
    private UserRepository userRepository;

    private String email = "test@example.com";
    private String password = "password";
    private AuthValidator<AuthenticationRequest> authValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        authValidator = new AuthValidator<>(userRepository);
    }

    @Test
    void testAuthRegisterValidate_NoExceptionThrown() {
        // Given
        AuthenticationRequest authRequest = AuthenticationRequest.builder()
                .password(password)
                .email(email)
                .build();

        // When
        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

        // Then
        assertDoesNotThrow(() -> authValidator.authRegisterValidate(authRequest));
    }

    @Test
    void testAuthRegisterValidate_FieldsError_ExceptionThrown() {
        // Given
        AuthenticationRequest authRequest = new AuthenticationRequest();

        // Then
        assertThrows(DefaultValidationException.class, () -> authValidator.authRegisterValidate(authRequest));
    }

    @Test
    void testAuthRegisterValidate_ExistingUser_ExceptionThrown() {
        // Given
        AuthenticationRequest authRequest = AuthenticationRequest.builder()
                .password(password)
                .email(email)
                .build();

        // Mock actions
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(new User()));

        // Then
        assertThrows(CustomBadRequestException.class, () -> authValidator.authRegisterValidate(authRequest));
    }

}