package com.app.classes.controllers;

import com.app.classes.entities.Course;
import com.app.classes.services.CoursesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/courses")
public class CoursesController {
    private final CoursesService coursesService;

    public CoursesController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        Optional<List<Course>> list = coursesService.findAllCourses();
        return new ResponseEntity<>(list.orElse(new ArrayList<>()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Integer id) {
        Optional<Course> existingCourse = coursesService.findByCourseId(id);
        return existingCourse.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        Optional<Course> createdCourse = coursesService.createCourse(course);
        return createdCourse.map(value -> new ResponseEntity<>(value, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("{id}")
    public ResponseEntity<Course> update(@PathVariable Integer id,
                                         @RequestBody Course course) {
        Optional<Course> existingCourse = coursesService.findByCourseId(id);
        if (existingCourse.isPresent()) {
            Optional<Course> updatedCourse = coursesService.updateCourse(id, course);
            return updatedCourse.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Course> existingCourse = coursesService.findByCourseId(id);
        if (existingCourse.isPresent()) {
            coursesService.deleteCourse(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

















