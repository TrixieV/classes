package com.app.classes.services;

import com.app.classes.entities.Student;

import java.util.List;
import java.util.Optional;

public interface StudentsService {
    List<Student> findAllStudent();
    Optional<Student> findByStudentId(Integer id);
    Optional<Student> createStudent(Student student);
    Optional<Student> updateStudent(Integer id, Student student);
    void deleteStudent(Integer id);
}
