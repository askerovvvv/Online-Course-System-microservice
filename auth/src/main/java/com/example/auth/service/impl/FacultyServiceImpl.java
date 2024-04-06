package com.example.auth.service.impl;

import com.example.auth.exceptions.NotFoundException;
import com.example.auth.mapper.FacultyMapper;
import com.example.auth.models.entity.Faculty;
import com.example.auth.models.requestsDto.FacultyRequestDto;
import com.example.auth.models.responsesDto.FacultyResponseDto;
import com.example.auth.repository.FacultyRepository;
import com.example.auth.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    @Override
    public void addFaculty(FacultyRequestDto facultyRequestData) {
        facultyRepository.save(FacultyMapper.INSTANCE.toFaculty(facultyRequestData));
    }

    @Override
    public List<FacultyResponseDto> findAllFaculties() {
        return FacultyMapper.INSTANCE.toFacultyResponsesDto(facultyRepository.findAll());
    }

    @Override
    public FacultyResponseDto findFacultyById(Integer facultyId) {
        return FacultyMapper.INSTANCE.toFacultyResponseDto(facultyRepository.findById(facultyId)
                .orElseThrow(() -> new NotFoundException("Faculty not found!")));
    }

    @Override
    public void deleteFacultyById(Integer facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new NotFoundException("Faculty not found!"));
        facultyRepository.delete(faculty);
    }
}
