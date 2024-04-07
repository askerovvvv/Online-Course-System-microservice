package com.example.auth.service.impl;

import com.example.auth.exceptions.DefaultValidationException;
import com.example.auth.exceptions.NotFoundException;
import com.example.auth.models.dto.CustomValidationErrorDto;
import com.example.auth.models.entity.Faculty;
import com.example.auth.models.requestsDto.FacultyRequestDto;
import com.example.auth.models.responsesDto.FacultyResponseDto;
import com.example.auth.repository.FacultyRepository;
import com.example.auth.service.FacultyService;
import com.example.auth.validator.CustomValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FacultyServiceImplTest {

    @Mock
    private FacultyRepository facultyRepository;
    @Mock
    private CustomValidator<FacultyRequestDto> facultyValidator;

    private FacultyService facultyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        facultyService = new FacultyServiceImpl(
                facultyRepository,
                facultyValidator
        );
    }

    @Test
    void testFindAllFaculties() {
        // Given
        Faculty mockFaculty1 = new Faculty();
        mockFaculty1.setFacultyName("Test1");

        Faculty mockFaculty2 = new Faculty();
        mockFaculty2.setFacultyName("Test2");

        List<Faculty> mockFaculties = new ArrayList<>();
        mockFaculties.add(mockFaculty1);
        mockFaculties.add(mockFaculty2);

        FacultyResponseDto mockFacultyResponseDto1 = new FacultyResponseDto();
        mockFacultyResponseDto1.setFacultyName("Test1");

        FacultyResponseDto mockFacultyResponseDto2 = new FacultyResponseDto();
        mockFacultyResponseDto2.setFacultyName("Test2");


        List<FacultyResponseDto> mockFacultyResponseDtos = new ArrayList<>();
        mockFacultyResponseDtos.add(mockFacultyResponseDto1);
        mockFacultyResponseDtos.add(mockFacultyResponseDto2);


        // Mock actions
        when(facultyRepository.findAll()).thenReturn(mockFaculties);

        // When
        List<FacultyResponseDto> actualResult = facultyService.findAllFaculties();

        // Then
        assertEquals(2, actualResult.size());
        assertEquals(mockFacultyResponseDtos, actualResult);
    }

    @Test
    void testAddFaculty_Success() {
        // Given
        FacultyRequestDto mockFacultyRequestDto = new FacultyRequestDto();
        Faculty mockFaculty = new Faculty();

        // Mock actions
        when(facultyValidator.validate(mockFacultyRequestDto)).thenReturn(Collections.emptyList());
        when(facultyRepository.save(any(Faculty.class))).thenReturn(mockFaculty);

        // When
        facultyService.addFaculty(mockFacultyRequestDto);

        // Then
        verify(facultyRepository, times(1)).save(mockFaculty);
    }

    @Test
    void testAddFaculty_ValidationError() {
        // Given
        FacultyRequestDto mockFacultyRequestDto = new FacultyRequestDto();

        // Mock actions
        when(facultyValidator.validate(mockFacultyRequestDto)).thenReturn(List.of(new CustomValidationErrorDto()));

        // Then
        assertThrows(DefaultValidationException.class, () -> facultyService.addFaculty(mockFacultyRequestDto));
    }

    @Test
    void testFindFacultyDtoById_Success() {
        // Given
        Faculty mockFaculty = new Faculty();

        // Mock actions
        when(facultyRepository.findById(1)).thenReturn(Optional.of(mockFaculty));

        // When
        FacultyResponseDto actualResult = facultyService.findFacultyDtoById(1);

        // Then
        assertNotNull(actualResult);
    }

    @Test
    void testFindFacultyDtoById_FacultyNotFoundException() {
        // Mock actions
        when(facultyRepository.findById(1)).thenReturn(Optional.empty());

        // Then
        assertThrows(NotFoundException.class, () -> facultyService.findFacultyDtoById(1));
    }

    @Test
    void testFindFacultyById_Success() {
        // Given
        Faculty mockFaculty = new Faculty();

        // Mock actions
        when(facultyRepository.findById(1)).thenReturn(Optional.of(mockFaculty));

        // When
        Faculty actualResult = facultyService.findFacultyById(1);

        // Then
        assertNotNull(actualResult);
    }

    @Test
    void testFindFacultyById_FacultyNotFoundException() {
        // Mock actions
        when(facultyRepository.findById(1)).thenReturn(Optional.empty());

        // Then
        assertThrows(NotFoundException.class, () -> facultyService.findFacultyById(1));
    }

    @Test
    void testDeleteFacultyById_Success() {
        // Given
        Faculty mockFaculty = new Faculty();

        // Mock actions
        when(facultyRepository.findById(1)).thenReturn(Optional.of(mockFaculty));

        // When
        facultyService.deleteFacultyById(1);

        // Then
        verify(facultyRepository, times(1)).delete(mockFaculty);
    }

    @Test
    void testDeleteFacultyById_NotFoundException() {
        // Mock actions
        when(facultyRepository.findById(1)).thenReturn(Optional.empty());

        // Then
        assertThrows(NotFoundException.class, () -> facultyService.deleteFacultyById(1));
    }
}