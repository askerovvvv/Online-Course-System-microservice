package com.example.auth.service;

import com.example.auth.models.requestsDto.RegisterRequest;
import com.example.auth.models.requestsDto.StudentRegisterRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {
    String studentRegister(StudentRegisterRequestDto studentRegisterData, MultipartFile userImageFile);

}
