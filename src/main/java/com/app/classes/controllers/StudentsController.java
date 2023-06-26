package com.app.classes.controllers;

import com.app.classes.entities.Student;
import com.app.classes.services.StudentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/students")
public class StudentsController {
    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        Optional<List<Student>> list = studentsService.findAllStudent();
        return new ResponseEntity<>(list.orElse(new ArrayList<>()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Integer id) {
        Optional<Student> student = studentsService.findByStudentId(id);
        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Optional<Student> createdStudent = studentsService.createStudent(student);
        return createdStudent.map(value -> new ResponseEntity<>(value, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> update(@PathVariable Integer id, @RequestBody Student student) {
        Optional<Student> checkingStudent = studentsService.findByStudentId(id);
        if (checkingStudent.isPresent()) {
            Optional<Student> updatedStudent = studentsService.updateStudent(id, student);
            return updatedStudent.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Student> student = studentsService.findByStudentId(id);
        if (student.isPresent()) {
            studentsService.deleteStudent(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

















