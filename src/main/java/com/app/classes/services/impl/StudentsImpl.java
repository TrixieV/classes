package com.app.classes.services.impl;

import com.app.classes.entities.Student;
import com.app.classes.repositories.StudentRepo;
import com.app.classes.services.StudentsService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsImpl implements StudentsService {
    private StudentRepo studentRepo;

    @Override
    public List<Student> findAllStudent() {
        return studentRepo.findAll();
    }

    @Override
    public Optional<Student> findByStudentId(Integer id) {
        try {
            return Optional.of(studentRepo.getReferenceById(id));
        } catch (EntityNotFoundException e) {
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
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public void deleteStudent(Integer id) {
        try {
            studentRepo.deleteById(id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
