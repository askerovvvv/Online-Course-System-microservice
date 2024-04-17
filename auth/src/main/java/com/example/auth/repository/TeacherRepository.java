package com.example.auth.repository;

import com.example.auth.models.entity.Teacher;
import com.example.auth.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
