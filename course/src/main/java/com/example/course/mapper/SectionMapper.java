package com.example.course.mapper;

import com.example.course.models.Section;
import com.example.course.models.requestsDto.SectionRequestDto;
import com.example.course.models.responsesDto.SectionResponseDto;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SectionMapper {

    SectionMapper INSTANCE = Mappers.getMapper(SectionMapper.class);

    Section toSection(SectionRequestDto sectionRequestDto);

    @InheritConfiguration
    @Mapping(target = "courseId", source = "section", qualifiedByName = "getCourseId")
    SectionResponseDto toSectionDto(Section section);

    List<SectionResponseDto> toSectionDtos(List<Section> sections);

    @Named("getCourseId")
    default Long getCourseId(Section section) {
        return section.getCourse().getId();
    }
}
