package com.company.educalink.service;

import com.company.educalink.entity.Qualification;

import java.util.List;

public interface QualificationService {

    Qualification saveQualification(Qualification qualification);
    Qualification findById(Long id);
    List<Qualification> getAll();
    Qualification updateQualification(Long id, Qualification qualification);
    void deleteById(Long id);

}
