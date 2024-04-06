package com.example.auth.service.impl;

import com.example.auth.config.JwtService;
import com.example.auth.exceptions.*;
import com.example.auth.models.requestsDto.AuthenticationRequest;
import com.example.auth.models.responsesDto.AuthenticationResponse;
import com.example.auth.models.requestsDto.RegisterRequest;
import com.example.auth.models.entity.EmailVerificationToken;
import com.example.auth.models.entity.Role;
import com.example.auth.models.entity.User;
import com.example.auth.models.entity.UserImage;
import com.example.auth.repository.UserRepository;
import com.example.auth.service.*;
import com.example.auth.validator.AuthValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final RoleService roleService;
    private final EmailVerificationService verificationService;
    private final JwtService jwtService;
    private final UserImageService userImageService;

    private final EmailSender emailSender;
    private final AuthValidator<RegisterRequest> registerValidator;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;



    @Override
    @Transactional
    public String authRegister(RegisterRequest registerData, MultipartFile userImageFile) {
        registerValidator.authRegisterValidate(registerData);


        UserImage userImage = userImageService.uploadImage(userImageFile, registerData.getEmail());
        User user = createUser(registerData, userImage);
        userRepository.save(user);

        EmailVerificationToken verificationToken = verificationService.createToken(user);
        String link = "http://localhost:8090/api/v1/auth/confirm?token=" + verificationToken.getToken();
        emailSender.send(registerData.getEmail(), emailSender.buildEmail(registerData.getFirstname(), link));

        return "Registered";
    }

    @Override
    public String confirmAccount(String token) {
        EmailVerificationToken verificationToken = verificationService.getByToken(token);

        if (verificationToken.getConfirmedAt() != null) {
            throw new ConflictException("Account has already been confirmed!");
        }

        LocalDateTime expiresAt = verificationToken.getExpiresAt();
        if (expiresAt.isBefore(LocalDateTime.now())) {
            userRepository.delete(verificationToken.getUser());
            verificationService.deleteToken(verificationToken);
            throw new NoLongerExistsException("Token has already expired! Please register again.");
        }

        verificationService.setConfirmedAt(token);
        userRepository.updateUserEmailVerified(verificationToken.getUser().getId());

        return "Your account has been successfully verified!";
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticateData) {
        try {
            authenticateUser(authenticateData);
        } catch (BadCredentialsException exception) {
            throw new CustomBadRequestException("Wrong login or password!");
        }
        User user = userRepository.findByEmail(authenticateData.getEmail())
                .orElseThrow(() -> new NotFoundException("User not found!"));
        String jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }

    private void authenticateUser(AuthenticationRequest authenticateData) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateData.getEmail(),
                        authenticateData.getPassword()
                )
        );
    }

    private User createUser(RegisterRequest registerData, UserImage userImage) {
        // TODO: give role "INACTIVE", then work globally with users without "Inactive"

        Role role = roleService.getRoleByName("ROLE_USER");

        return User.builder()
                .firstname(registerData.getFirstname())
                .lastname(registerData.getLastname())
                .email(registerData.getEmail())
                .role(new HashSet<>(List.of(role)))
                .password(passwordEncoder.encode(registerData.getPassword()))
                .emailVerified(false)
                .userImage(userImage)
                .build();
    }

}
