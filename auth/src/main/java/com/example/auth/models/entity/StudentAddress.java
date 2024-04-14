package com.example.auth.models.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class StudentAddress {
    private String livingCity;
    private String street;
    private String houseNum;
    private String zipcode;
}
