package com.example.course.mapper;

import com.example.course.models.Section;
import com.example.course.models.responsesDto.SectionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SectionMapper {

    SectionMapper INSTANCE = Mappers.getMapper(SectionMapper.class);
    @Mapping(target = "courseId", source = "section", qualifiedByName = "getCourseId")
    SectionDto toSectionDto(Section section);

    List<SectionDto> toSectionDtos(List<Section> sections);

    @Named("getCourseId")
    default Long getCourseId(Section section) {
        return section.getCourse().getId();
    }
}
