package com.app.classes.services.impl;

import com.app.classes.entities.Course;
import com.app.classes.repositories.CourseRepo;
import com.app.classes.services.CoursesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesImpl implements CoursesService {
    @Autowired
    private CourseRepo courseRepo;

    @Override
    public Optional<List<Course>> findAllCourses() {
        return Optional.of(courseRepo.findAll());
    }

    @Override
    public Optional<Course> findByCourseId(Integer id) {
        try {
            return courseRepo.findById(id);
        } catch (Exception e) {
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
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteCourse(Integer id) {
        try {
            courseRepo.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
