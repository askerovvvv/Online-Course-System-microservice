package com.example.auth.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String filePath;

    @Override
    public String toString() {
        return "UserImage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserImage userImage = (UserImage) o;
        return Objects.equals(id, userImage.id) && Objects.equals(name, userImage.name) && Objects.equals(type, userImage.type) && Objects.equals(filePath, userImage.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, filePath);
    }
}
