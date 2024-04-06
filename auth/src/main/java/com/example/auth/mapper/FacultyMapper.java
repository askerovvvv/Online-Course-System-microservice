package com.example.auth.mapper;

import com.example.auth.models.entity.Faculty;
import com.example.auth.models.requestsDto.FacultyRequestDto;
import com.example.auth.models.responsesDto.FacultyResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacultyMapper {

    FacultyMapper INSTANCE = Mappers.getMapper(FacultyMapper.class);

    Faculty toFaculty(FacultyRequestDto facultyRequestDto);
    FacultyResponseDto toFacultyResponseDto(Faculty faculty);

    List<Faculty> toFaculties(List<FacultyRequestDto> facultyRequestsDto);
    List<FacultyResponseDto> toFacultyResponsesDto(List<Faculty> faculties);

}
