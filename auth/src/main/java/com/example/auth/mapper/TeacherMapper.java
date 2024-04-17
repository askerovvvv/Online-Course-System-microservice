package com.example.auth.mapper;

import com.example.auth.models.entity.Teacher;
import com.example.auth.models.responsesDto.TeacherResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

//    TeacherResponseDto toTeacherResponseDto(Teacher teacher);
//    Teacher toTeacher(Teacher teacher);

    List<TeacherResponseDto> toTeacherResponseDtos(List<Teacher> teachers);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "lastname", target = "lastname")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "emailVerified", target = "emailVerified")
    @Mapping(source = "experience", target = "experience")
    @Mapping(source = "salary", target = "salary")
    @Mapping(source = "officeNumber", target = "officeNumber")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "hireDate", target = "hireDate")
    TeacherResponseDto toTeacherResponseDto(Teacher teacher);

}