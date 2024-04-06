package com.example.auth.service.impl;

import com.example.auth.exceptions.DefaultValidationException;
import com.example.auth.exceptions.NotFoundException;
import com.example.auth.mapper.GroupMapper;
import com.example.auth.models.dto.CustomValidationErrorDto;
import com.example.auth.models.entity.Faculty;
import com.example.auth.models.entity.Group;
import com.example.auth.models.requestsDto.GroupRequestDto;
import com.example.auth.models.responsesDto.GroupResponseDto;
import com.example.auth.repository.GroupRepository;
import com.example.auth.service.FacultyService;
import com.example.auth.service.GroupService;
import com.example.auth.validator.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final FacultyService facultyService;
    private final CustomValidator<GroupRequestDto> groupValidator;

    @Override
    public List<GroupResponseDto> findAllGroups() {
        return GroupMapper.INSTANCE.toGroupResponsesDto(groupRepository.findAll());
    }

    @Override
    public void addGroup(GroupRequestDto groupRequestData) {
        List<CustomValidationErrorDto> validate = groupValidator.validate(groupRequestData);
        if (!validate.isEmpty()) {
            throw new DefaultValidationException("validation error", validate);
        }

        Faculty faculty = facultyService.findFacultyById(groupRequestData.getFacultyId());
        Group group = GroupMapper.INSTANCE.toGroup(groupRequestData);
        group.setFaculty(faculty);

        groupRepository.save(group);
    }

    @Override
    public GroupResponseDto findGroupById(Long groupId) {
        return GroupMapper.INSTANCE.toGroupResponseDto(groupRepository.findById(groupId)
                .orElseThrow(() -> new NotFoundException("Group not found!")));
    }

    @Override
    public void deleteGroupById(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new NotFoundException("Group not found!"));

        groupRepository.delete(group);
    }
}
