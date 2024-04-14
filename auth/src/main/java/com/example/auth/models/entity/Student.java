package com.example.auth.models.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student extends User {
                  // TODO: make student group and faculty other tables
    private int credit;
    private String citizenship;

    @ManyToOne
//    @JoinColumn
    private Group studentGroup;

    @Embedded
    private StudentAddress studentAddress;

    @Override
    public String toString() {
        return "Student{" +
                "credit=" + credit +
                ", citizenship='" + citizenship + '\'' +
                ", studentGroup=" + studentGroup +
                ", studentAddress=" + studentAddress +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return credit == student.credit && Objects.equals(citizenship, student.citizenship) && Objects.equals(studentGroup, student.studentGroup) && Objects.equals(studentAddress, student.studentAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), credit, citizenship, studentGroup, studentAddress);
    }
}
