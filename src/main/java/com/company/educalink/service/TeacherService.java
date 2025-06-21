package com.company.educalink.service;

import com.company.educalink.entity.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher saveTeacher(Teacher teacher);
    Teacher findById(Long id);
    List<Teacher> getAll();
    Teacher updateTeacher(Long id, Teacher teacher);
    void deleteById(Long id);
}
