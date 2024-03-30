package com.example.course.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Section extends BaseEntity {

    private String title;
    private int sectionOrder;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}
