package com.example.auth.service.impl;

import com.example.auth.exceptions.DefaultValidationException;
import com.example.auth.exceptions.NotFoundException;
import com.example.auth.models.dto.CustomValidationErrorDto;
import com.example.auth.models.entity.Faculty;
import com.example.auth.models.entity.Group;
import com.example.auth.models.requestsDto.GroupRequestDto;
import com.example.auth.models.responsesDto.GroupResponseDto;
import com.example.auth.repository.GroupRepository;
import com.example.auth.service.FacultyService;
import com.example.auth.service.GroupService;
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

class GroupServiceImplTest {

    @Mock
    private GroupRepository groupRepository;
    @Mock
    private FacultyService facultyService;
    @Mock
    private CustomValidator<GroupRequestDto> groupValidator;

    GroupService groupService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        groupService = new GroupServiceImpl(
                groupRepository,
                facultyService,
                groupValidator
        );
    }

    @Test
    void testFindAllGroups() {
        // Given
        List<GroupResponseDto> mockGroupResponseDtos = new ArrayList<>();
        mockGroupResponseDtos.add(GroupResponseDto.builder()
                .numberOfStudents(0)
                .facultyId(1)
                .build());
        mockGroupResponseDtos.add(GroupResponseDto.builder()
                .numberOfStudents(0)
                .facultyId(1)
                .build());

        Faculty mockFaculty = new Faculty();
        mockFaculty.setId(1);

        List<Group> mockGroups = new ArrayList<>();
        mockGroups.add(Group.builder()
                .faculty(mockFaculty)
                .studentsInGroup(new ArrayList<>())
                .build());
        mockGroups.add(Group.builder()
                .faculty(mockFaculty)
                .studentsInGroup(new ArrayList<>())
                .build());

        // Mock actions
        when(groupRepository.findAll()).thenReturn(mockGroups);

        // When
        List<GroupResponseDto> actualResult = groupService.findAllGroups();

        assertEquals(2, actualResult.size());
        assertEquals(mockGroupResponseDtos, actualResult);
    }

    @Test
    void testAddGroup_Success() {
        // Given
        GroupRequestDto mockGroupRequestDto = new GroupRequestDto();
        mockGroupRequestDto.setFacultyId(1);

        Faculty mockFaculty = new Faculty();

        Group mockGroup = new Group();
        mockGroup.setFaculty(mockFaculty);

        // Mock actions
        when(groupValidator.validate(mockGroupRequestDto)).thenReturn(Collections.emptyList());
        when(facultyService.findFacultyById(1)).thenReturn(mockFaculty);
        when(groupRepository.save(any(Group.class))).thenReturn(mockGroup);

        // When
        groupService.addGroup(mockGroupRequestDto);

        // Then
        verify(groupRepository, times(1)).save(mockGroup);
    }

    @Test
    void testAddGroup_FacultyNotFoundException() {
        // Given
        GroupRequestDto mockGroupRequestDto = new GroupRequestDto();
        mockGroupRequestDto.setFacultyId(1);

        // Mock actions
        when(groupValidator.validate(mockGroupRequestDto)).thenReturn(Collections.emptyList());
        when(facultyService.findFacultyById(1)).thenThrow(NotFoundException.class);

        // Then
        assertThrows(NotFoundException.class, () -> groupService.addGroup(mockGroupRequestDto));
    }

    @Test
    void testAddGroup_ValidationError() {
        // Given
        GroupRequestDto mockGroupRequestDto = new GroupRequestDto();

        // Mock actions
        when(groupValidator.validate(mockGroupRequestDto)).thenReturn(List.of(new CustomValidationErrorDto()));

        // Then
        assertThrows(DefaultValidationException.class, () -> groupService.addGroup(mockGroupRequestDto));
    }

    @Test
    void testFindGroupById_Success() {
        // Given
        Faculty mockFaculty = new Faculty();
        mockFaculty.setId(1);

        Group mockGroup = new Group();
        mockGroup.setFaculty(mockFaculty);

        GroupResponseDto mockGroupResponseDto = new GroupResponseDto();
        mockGroupResponseDto.setFacultyId(1);
        mockGroupResponseDto.setNumberOfStudents(0);

        // Mock actions
        when(groupRepository.findById(3L)).thenReturn(Optional.of(mockGroup));

        // When
        GroupResponseDto actualResult = groupService.findGroupById(3L);

        // Then
        assertEquals(mockGroupResponseDto, actualResult);
    }

    @Test
    void testFindGroupById_GroupNotFoundException() {

        // Mock actions
        when(groupRepository.findById(3L)).thenReturn(Optional.empty());

        // Then
        assertThrows(NotFoundException.class, () -> groupService.findGroupById(3L));
    }

    @Test
    void testDeleteGroupById_Success() {
        // Given
        Faculty mockFaculty = new Faculty();
        mockFaculty.setId(1);

        Group mockGroup = new Group();
        mockGroup.setFaculty(mockFaculty);

        // Mock actions
        when(groupRepository.findById(3L)).thenReturn(Optional.of(mockGroup));

        // When
        groupService.deleteGroupById(3L);

        // Then
        verify(groupRepository, times(1)).delete(mockGroup);
    }

    @Test
    void testDeleteGroupById_GroupNotFoundException() {
        // Mock actions
        when(groupRepository.findById(3L)).thenReturn(Optional.empty());

        // Then
        assertThrows(NotFoundException.class, () -> groupService.deleteGroupById(3L));
    }
}