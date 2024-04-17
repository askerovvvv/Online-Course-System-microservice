package com.example.auth.service.impl;

import com.example.auth.models.requestsDto.StudentRegisterRequestDto;
import com.example.auth.repository.StudentRepository;
import com.example.auth.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final AuthServiceImpl authService;

    @Override
    public String studentRegister(StudentRegisterRequestDto studentRegisterData, MultipartFile userImageFile) {
//        return authService.authRegister(studentRegisterData, userImageFile);
        return null;
    }
}
