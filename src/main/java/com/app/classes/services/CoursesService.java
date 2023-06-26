package com.app.classes.services;

import com.app.classes.entities.Course;

import java.util.List;
import java.util.Optional;

public interface CoursesService {
    List<Course> findAllCourses();
    Optional<Course> findByCourseId(Integer id);
    Optional<Course> createCourse(Course course);
    Optional<Course> updateCourse(Integer id, Course course);
    void deleteCourse(Integer id);
}
