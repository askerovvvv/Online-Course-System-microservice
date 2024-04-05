package com.example.course.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "base_sequence"
    )
    @SequenceGenerator(
            name = "base_sequence",
            sequenceName = "base_sequence",
            allocationSize = 1
    )
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

}
