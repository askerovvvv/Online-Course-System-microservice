package com.example.auth.mapper;

import com.example.auth.models.entity.Group;
import com.example.auth.models.requestsDto.GroupRequestDto;
import com.example.auth.models.responsesDto.GroupResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")

public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    Group toGroup(GroupRequestDto groupRequestDto);

    @Mapping(target = "facultyId", source = "group", qualifiedByName = "getFacultyId")
    GroupResponseDto toGroupResponseDto(Group group);

    List<Group> toGroups(List<GroupRequestDto> groupRequestsDto);
    List<GroupResponseDto> toGroupResponsesDto(List<Group> groups);

    @Named("getFacultyId")
    default Integer getFacultyId(Group group) {
        return group.getFaculty().getId();
    }
}
