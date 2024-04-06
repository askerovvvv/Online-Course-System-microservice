package com.example.auth.service.impl;

import com.example.auth.exceptions.DefaultValidationException;
import com.example.auth.exceptions.NotFoundException;
import com.example.auth.mapper.FacultyMapper;
import com.example.auth.models.dto.CustomValidationErrorDto;
import com.example.auth.models.entity.Faculty;
import com.example.auth.models.requestsDto.FacultyRequestDto;
import com.example.auth.models.responsesDto.FacultyResponseDto;
import com.example.auth.repository.FacultyRepository;
import com.example.auth.service.FacultyService;
import com.example.auth.validator.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final CustomValidator<FacultyRequestDto> facultyValidator;

    @Override
    public List<FacultyResponseDto> findAllFaculties() {
        return FacultyMapper.INSTANCE.toFacultyResponsesDto(facultyRepository.findAll());
    }

    @Override
    public void addFaculty(FacultyRequestDto facultyRequestData) {
        List<CustomValidationErrorDto> validate = facultyValidator.validate(facultyRequestData);
        if (!validate.isEmpty()) {
            throw new DefaultValidationException("validation error", validate);
        }

        facultyRepository.save(FacultyMapper.INSTANCE.toFaculty(facultyRequestData));
    }

    @Override
    public FacultyResponseDto findFacultyDtoById(Integer facultyId) {
        return FacultyMapper.INSTANCE.toFacultyResponseDto(facultyRepository.findById(facultyId)
                .orElseThrow(() -> new NotFoundException("Faculty not found!")));
    }

    @Override
    public Faculty findFacultyById(Integer facultyId) {
        return facultyRepository.findById(facultyId)
                .orElseThrow(() -> new NotFoundException("Faculty not found!"));
    }

    @Override
    public void deleteFacultyById(Integer facultyId) {
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new NotFoundException("Faculty not found!"));
        facultyRepository.delete(faculty);
    }
}
