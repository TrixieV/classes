package com.app.classes.services.impl;

import com.app.classes.entities.Course;
import com.app.classes.repositories.CourseRepo;
import com.app.classes.services.CoursesService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesImpl implements CoursesService {
    private CourseRepo courseRepo;
    @Override
    public List<Course> findAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public Optional<Course> findByCourseId(Integer id) {
        try {
            return Optional.of(courseRepo.getReferenceById(id));
        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Course> createCourse(Course course) {
        return Optional.of(courseRepo.save(course));
    }

    @Override
    public Optional<Course> updateCourse(Integer id, Course course) {
        try {
            Course existingCourse = courseRepo.getReferenceById(id);
            BeanUtils.copyProperties(course, existingCourse, "id");
            return Optional.of(courseRepo.save(existingCourse));
        } catch (EntityNotFoundException e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteCourse(Integer id) {
        try {
            courseRepo.deleteById(id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
