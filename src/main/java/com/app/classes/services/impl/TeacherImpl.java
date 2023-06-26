package com.app.classes.services.impl;

import com.app.classes.entities.Teacher;
import com.app.classes.repositories.TeacherRepo;
import com.app.classes.services.TeachersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherImpl implements TeachersService {
    @Autowired
    private TeacherRepo teacherRepo;

    @Override
    public Optional<List<Teacher>> findAllTeachers() {
        return Optional.of(teacherRepo.findAll());
    }

    @Override
    public Optional<Teacher> findByTeacherId(Integer id) {
        try {
            return teacherRepo.findById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<Teacher> createTeacher(Teacher teacher) {
        return Optional.of(teacherRepo.save(teacher));
    }

    @Override
    public Optional<Teacher> updateTeacher(Integer id, Teacher teacher) {
        try {
            Teacher existingTeacher = teacherRepo.getReferenceById(id);
            BeanUtils.copyProperties(teacher, existingTeacher, "id");
            return Optional.of(teacherRepo.save(existingTeacher));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void deleteTeacher(Integer id) {
        try {
            teacherRepo.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
