package com.company.educalink.service;

import com.company.educalink.entity.Homework;

import java.util.List;

public interface HomeworkService {
    Homework saveHomework(Homework homework);
    Homework findById(Long id);
    List<Homework> getAll();
    Homework updateHomework(Long id, Homework homework);
    void deleteById(Long id);
}
