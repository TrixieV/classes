package com.app.classes.services.impl;

import com.app.classes.entities.Student;
import com.app.classes.repositories.StudentRepo;
import com.app.classes.services.StudentsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsImpl implements StudentsService {
    @Autowired
    private StudentRepo studentRepo;

    @Override
    public Optional<List<Student>> findAllStudent() {
        return Optional.of(studentRepo.findAll());
    }

    @Override
    public Optional<Student> findByStudentId(Integer id) {
        try {
            return studentRepo.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Student> createStudent(Student student) {
        return Optional.of(studentRepo.save(student));
    }

    @Override
    public Optional<Student> updateStudent(Integer id, Student student) {
        try {
            Student existingStudent = studentRepo.getReferenceById(id);
            BeanUtils.copyProperties(student, existingStudent, "id");
            return Optional.of(studentRepo.save(existingStudent));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void deleteStudent(Integer id) {
        try {
            studentRepo.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
