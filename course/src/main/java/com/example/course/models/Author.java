package com.example.course.models;


import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Author extends BaseEntity{

    private String firstname;
    private String lastname;
    private String email;
    // TODO: photo
//    private String photo;

}
