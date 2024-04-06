package com.example.auth.service;

import com.example.auth.models.requestsDto.FacultyRequestDto;
import com.example.auth.models.requestsDto.GroupRequestDto;
import com.example.auth.models.responsesDto.FacultyResponseDto;
import com.example.auth.models.responsesDto.GroupResponseDto;

import java.util.List;

public interface GroupService {

    List<GroupResponseDto> findAllGroups();

    void addGroup(GroupRequestDto groupRequestData);

    GroupResponseDto findGroupById(Long groupId);

    void deleteGroupById(Long groupId);
}
