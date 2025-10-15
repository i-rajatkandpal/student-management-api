package com.example.student_management;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByCourse(String course);
    boolean existsByEmail(String email);
}
