package com.example.auth.service;

import com.example.auth.models.entity.Faculty;
import com.example.auth.models.requestsDto.FacultyRequestDto;
import com.example.auth.models.responsesDto.FacultyResponseDto;

import java.util.List;

public interface FacultyService {
    void addFaculty(FacultyRequestDto facultyRequestData);

    List<FacultyResponseDto> findAllFaculties();

    FacultyResponseDto findFacultyDtoById(Integer facultyId);
    Faculty findFacultyById(Integer facultyId);

    void deleteFacultyById(Integer facultyId);
}
