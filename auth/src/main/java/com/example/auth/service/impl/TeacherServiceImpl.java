package com.example.auth.service.impl;

import com.example.auth.client.AuthCourseClient;
import com.example.auth.exceptions.NotFoundException;
import com.example.auth.mapper.TeacherMapper;
import com.example.auth.models.entity.Teacher;
import com.example.auth.models.responsesDto.TeacherResponseDto;
import com.example.auth.repository.TeacherRepository;
import com.example.auth.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final AuthCourseClient authCourseClient;

    @Override
    public List<TeacherResponseDto> findAllTeachers() {
//        TeacherResponseDto teacherResponseDto = TeacherResponseDto.builder()
//                .id()
//                .build()
        return TeacherMapper.INSTANCE.toTeacherResponseDtos(teacherRepository.findAll());
    }

    @Override
    public TeacherResponseDto findTeacherDtoById(Long id) {
        return TeacherMapper.INSTANCE.toTeacherResponseDto(teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher with this id is not found!")));
    }

    @Override
    public Teacher findTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher with this id is not found!"));
    }

    @Override
    public void deleteTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Teacher with this id is not found!"));

        authCourseClient.deleteAuthorById(id);
        teacherRepository.delete(teacher);
    }
}
