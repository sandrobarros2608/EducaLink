package com.company.educalink.service;

import com.company.educalink.entity.Submission;

import java.util.List;

public interface SubmissionService {

    Submission saveSubmission(Submission submission);
    Submission findById(Long id);
    List<Submission> getAll();
    Submission updateSubmission(Long id, Submission submission);
    void deleteById(Long id);
}
