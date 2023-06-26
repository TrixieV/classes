package com.app.classes.controllers;

import com.app.classes.entities.Teacher;
import com.app.classes.services.TeachersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/teachers")
public class TeachersController {
    private final TeachersService teachersService;

    public TeachersController(TeachersService teachersService) {
        this.teachersService = teachersService;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        Optional<List<Teacher>> list = teachersService.findAllTeachers();
        return new ResponseEntity<>(list.orElse(new ArrayList<>()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable Integer id) {
        Optional<Teacher> existingTeacher = teachersService.findByTeacherId(id);
        return existingTeacher.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Teacher> create(@RequestBody Teacher teacher) {
        Optional<Teacher> createdTeacher = teachersService.createTeacher(teacher);
        return createdTeacher.map(value -> new ResponseEntity<>(value, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("{id}")
    public ResponseEntity<Teacher> update(@PathVariable Integer id,
                                          @RequestBody Teacher teacher) {
        Optional<Teacher> existingTeacher = teachersService.findByTeacherId(id);
        if (existingTeacher.isPresent()) {
            Optional<Teacher> updatedTeacher = teachersService.updateTeacher(id, teacher);
            return updatedTeacher.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Teacher> existingTeacher = teachersService.findByTeacherId(id);
        if (existingTeacher.isPresent()) {
            teachersService.deleteTeacher(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}





















