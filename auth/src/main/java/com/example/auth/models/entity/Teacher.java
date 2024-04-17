package com.example.auth.models.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher extends User {
    private int experience;
    private String officeNumber;
    private double salary;
    private String phoneNumber;
    private LocalDateTime hireDate;
}
