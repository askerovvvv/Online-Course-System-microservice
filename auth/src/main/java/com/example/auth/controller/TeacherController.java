package com.example.auth.controller;

import com.example.auth.models.requestsDto.FacultyRequestDto;
import com.example.auth.models.responsesDto.FacultyResponseDto;
import com.example.auth.models.responsesDto.TeacherResponseDto;
import com.example.auth.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/get/teachers")
    public ResponseEntity<List<TeacherResponseDto>> findAllTeachers() {
        return ResponseEntity.status(HttpStatus.OK).body(teacherService.findAllTeachers());
    }

    @GetMapping("/get/teacher/by")
    public ResponseEntity<TeacherResponseDto> findTeacherById(@RequestParam("teacherId") Long teacherId) {

        return ResponseEntity.status(HttpStatus.OK).body(teacherService.findTeacherDtoById(teacherId));
    }


}
