package com.company.educalink.service;

import com.company.educalink.entity.Qualification;
import com.company.educalink.exception.custom.ResourceNotFoundException;
import com.company.educalink.repository.QualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QualificationServiceImpl implements QualificationService {

    @Autowired
    private QualificationRepository qualificationRepository;

    @Override
    public Qualification saveQualification(Qualification qualification) {
        return qualificationRepository.save(qualification);
    }

    @Override
    public Qualification findById(Long id) {
        return qualificationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Qualification.class, id));
    }

    @Override
    public List<Qualification> getAll() {
        return qualificationRepository.findAll();
    }

    @Override
    public Qualification updateQualification(Long id, Qualification qualification) {
        Optional<Qualification> optional = qualificationRepository.findById(id);
        if (optional.isPresent()) {
            Qualification existing = optional.get();
            existing.setScore(qualification.getScore());
            existing.setFeedback(qualification.getFeedback());
            existing.setEvaluationDate(qualification.getEvaluationDate());
            //existing.setSubmission(qualification.getSubmission());
            return qualificationRepository.save(existing);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        qualificationRepository.deleteById(id);
    }
}
