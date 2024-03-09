package com.example.auth.exceptions;

import com.example.auth.models.dto.CustomValidationErrorDto;
import lombok.Getter;

import java.util.List;

@Getter
public class DefaultValidationException extends RuntimeException {
    List<CustomValidationErrorDto> fieldErrors;
    public DefaultValidationException(String message, List<CustomValidationErrorDto> fieldsErrors) {
        super(message);
        this.fieldErrors = fieldsErrors;
    }

}
