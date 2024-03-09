package com.example.auth.service.impl;

import com.example.auth.config.JwtService;
import com.example.auth.exceptions.*;
import com.example.auth.models.dto.AuthenticationRequest;
import com.example.auth.models.dto.AuthenticationResponse;
import com.example.auth.models.dto.RegisterRequest;
import com.example.auth.models.entity.EmailVerificationToken;
import com.example.auth.models.entity.User;
import com.example.auth.repository.EmailVerificationRepository;
import com.example.auth.repository.RoleRepository;
import com.example.auth.repository.UserRepository;
import com.example.auth.service.AuthService;
import com.example.auth.service.EmailSender;
import com.example.auth.service.EmailVerificationService;
import com.example.auth.validator.AuthValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final EmailVerificationRepository verificationRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmailVerificationService verificationService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final AuthValidator<RegisterRequest> registerValidator;
    private final EmailSender emailSender;

    @Override
    public String authRegister(RegisterRequest registerData) {
        // TODO: complete all TODO, and other
        registerValidator.authRegisterValidate(registerData);

        User user = createUser(registerData);
        userRepository.save(user);

        EmailVerificationToken verificationToken = verificationService.createToken(user);
        String link = "http://localhost:8090/api/v1/auth/confirm?token=" + verificationToken.getToken();
        emailSender.send(registerData.getEmail(), emailSender.buildEmail(registerData.getFirstname(), link));

        return "Registered";
    }

    @Override
    public String confirmAccount(String token) {
        EmailVerificationToken verificationToken = verificationRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundException("Account not found!"));

        if (verificationToken.getConfirmedAt() != null) {
            throw new ConflictException("Account has already been confirmed!");
        }

        LocalDateTime expiresAt = verificationToken.getExpiresAt();
        if (expiresAt.isBefore(LocalDateTime.now())) {
            userRepository.delete(verificationToken.getUser());
            verificationRepository.delete(verificationToken);
            throw new NoLongerExistsException("Token has already expired! Please register again.");
        }

        verificationService.setConfirmedAt(token);
        userRepository.updateUserEmailVerified(verificationToken.getUser().getId());

        return "Your account has been successfully verified!";
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest requestData) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestData.getEmail(),
                            requestData.getPassword()
                    )
            );
        } catch (BadCredentialsException exception) { // TODO: что за исключение юзенр не активен или пароль невкерен
            throw new CustomBadRequestException("Wrong login or password!");
        }
        User user = userRepository.findByEmail(requestData.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found!"));
        String jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }


    // TODO: think how to improve (try)
    private Authentication authenticateUser(AuthenticationRequest requestData) {
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestData.getEmail(),
                        requestData.getPassword()
                )
        );
    }

    private User createUser(RegisterRequest registerData) {
//        Role role = roleRepository.findByName("ROLE_USER").get();

        return User.builder()
                .firstname(registerData.getFirstname())
                .lastname(registerData.getLastname())
                .email(registerData.getEmail())
                .role(new HashSet<>(List.of()))
                .password(passwordEncoder.encode(registerData.getPassword()))
                .emailVerified(false)
                .build();
    }

}
