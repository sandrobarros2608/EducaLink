package com.company.educalink.service;

import com.company.educalink.entity.Submission;
import com.company.educalink.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubmissionServiceImpl implements SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Override
    public Submission saveSubmission(Submission submission) {
        return submissionRepository.save(submission);
    }

    @Override
    public Submission findById(Long id) {
        return submissionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Submission> getAll() {
        return submissionRepository.findAll();
    }

    @Override
    public Submission updateSubmission(Long id, Submission submission) {
        Optional<Submission> optional = submissionRepository.findById(id);
        if (optional.isPresent()) {
            Submission existing = optional.get();
            existing.setContent(submission.getContent());
            existing.setSubmissionDate(submission.getSubmissionDate());
            existing.setSubmitted(submission.isSubmitted());
            existing.setComments(submission.getComments());
            existing.setHomework(submission.getHomework());
            existing.setStudent(submission.getStudent());
            return submissionRepository.save(existing);
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        submissionRepository.deleteById(id);
    }
}
