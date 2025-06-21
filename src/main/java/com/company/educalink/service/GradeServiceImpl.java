package com.company.educalink.service;

import com.company.educalink.entity.Grade;
import com.company.educalink.exception.custom.GradeNotFoundException;
import com.company.educalink.repository.CourseRepository;
import com.company.educalink.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    @Override
    public Grade findById(Long id) {
        return gradeRepository.findById(id).orElseThrow(() -> new GradeNotFoundException(id));
    }

    @Override
    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }

    @Override
    public Grade updateGrade(Long id, Grade grade) {
        Grade existingGrade = gradeRepository.findById(id)
                .orElseThrow(() -> new GradeNotFoundException(id));

        existingGrade.setName(grade.getName());
        return gradeRepository.save(existingGrade);
    }

    @Override
    public void deleteById(Long id) {
        Grade existingGrade = gradeRepository.findById(id)
                .orElseThrow(() -> new GradeNotFoundException(id));
        gradeRepository.delete(existingGrade);
    }
}
