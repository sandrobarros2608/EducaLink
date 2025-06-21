package com.company.educalink.service;

import com.company.educalink.entity.Grade;

import java.util.List;

public interface GradeService {

    Grade saveGrade(Grade grade);
    Grade findById(Long id);
    List<Grade> getAll();
    Grade updateGrade(Long id, Grade grade);
    void deleteById(Long id);
}
