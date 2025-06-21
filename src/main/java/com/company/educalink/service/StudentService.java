package com.company.educalink.service;

import com.company.educalink.entity.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);
    Student findById(Long id);
    List<Student> getAll();
    Student updateStudent(Long id, Student student);
    void deleteById(Long id);
}
