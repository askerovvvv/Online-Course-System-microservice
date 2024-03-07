package com.example.auth.validator;

import com.example.auth.exceptions.CustomBadRequestException;
import com.example.auth.exceptions.DefaultValidationException;
import com.example.auth.models.dto.BaseAuthRequest;
import com.example.auth.models.dto.CustomValidationErrorDto;
import com.example.auth.models.dto.RegisterRequest;
import com.example.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AuthValidator<T extends BaseAuthRequest> extends CustomValidator<T> {

    private final UserRepository userRepository;

    public boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public void authRegisterValidate(T authObject) {
        List<CustomValidationErrorDto> fieldsError = super.validate(authObject);

        if (!fieldsError.isEmpty()) {
            throw new DefaultValidationException("Validation error!", fieldsError);
        }

        if (!userExists(authObject.getEmail())) {
            throw new CustomBadRequestException("This user already exists");
        }

    }
}
