package com.company.educalink.service;

import com.company.educalink.entity.Publication;

import java.util.List;

public interface PublicationService {

    Publication savePublication(Publication publication);
    List<Publication> getAll();
    Publication updatePublication(Long id, Publication publication);
    void deleteById(Long id);
}
