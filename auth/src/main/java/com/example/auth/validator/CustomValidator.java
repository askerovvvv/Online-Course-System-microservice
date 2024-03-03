package com.example.auth.validator;

import com.example.auth.models.dto.CustomValidationErrorDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomValidator<T> {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    public List<CustomValidationErrorDto> validate(T objectToValidate) {
        Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);

        return violations.stream()
                .map(violation -> new CustomValidationErrorDto(violation.getPropertyPath().toString(), violation.getMessage()))
                .collect(Collectors.toList());
    }

}
