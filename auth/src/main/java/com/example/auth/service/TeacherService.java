package com.example.auth.service;

import com.example.auth.models.entity.Teacher;
import com.example.auth.models.responsesDto.TeacherResponseDto;

import java.util.List;

public interface TeacherService {
    List<TeacherResponseDto> findAllTeachers();
    TeacherResponseDto findTeacherDtoById(Long id);
    Teacher findTeacherById(Long id);

    void deleteTeacherById(Long id);


}
