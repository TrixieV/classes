package com.app.classes.services;

import com.app.classes.entities.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeachersService {
    Optional<List<Teacher>> findAllTeachers();

    Optional<Teacher> findByTeacherId(Integer id);

    Optional<Teacher> createTeacher(Teacher teacher);

    Optional<Teacher> updateTeacher(Integer id, Teacher teacher);

    void deleteTeacher(Integer id);
}
