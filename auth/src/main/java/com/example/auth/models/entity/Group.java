package com.example.auth.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "group_")
public class Group {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "group_sequence"
    )
    @SequenceGenerator(
            name = "group_sequence",
            sequenceName = "group_sequence",
            allocationSize = 1
    )
    private Long id;
    private String groupName;
    @ManyToOne
//    @JoinColumn
    private Faculty faculty;

    @OneToMany(mappedBy="studentGroup", cascade = CascadeType.ALL)
    private List<Student> studentsInGroup = new ArrayList<>();

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", faculty=" + faculty +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) && Objects.equals(groupName, group.groupName) && Objects.equals(faculty, group.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupName, faculty);
    }
}
