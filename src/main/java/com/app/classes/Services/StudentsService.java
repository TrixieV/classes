package com.app.classes.Services;

import com.app.classes.repositories.StudentRepo;
import org.springframework.stereotype.Service;

@Service
public class StudentsService {
    private StudentRepo studentRepo;

    public StudentsService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


}
