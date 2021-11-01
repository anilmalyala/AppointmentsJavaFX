package com.csia.anish.service;


import com.csia.anish.data.Student;
import com.csia.anish.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepo studentRepo;

    public Student createStudent(Student student){
        return studentRepo.save(student);
    }

    public List getAllStudents(){
        Iterable<Student> studentIterable = studentRepo.findAll();
        List<Student> students=new ArrayList<>();
        studentIterable.forEach(students::add);
        return students;
    }

    public Optional<Student> getStudent(Long studentId) {
        return studentRepo.findById(studentId);
    }

    public Student updateStudent(Student student) {
        return studentRepo.save(student);

    }
}
