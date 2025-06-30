package com.company.educalink.service;

import com.company.educalink.entity.Publication;
import com.company.educalink.exception.custom.PublicationNotFoundException;
import com.company.educalink.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public Publication savePublication(Publication publication) {
        return publicationRepository.save(publication);
    }

    @Override
    public List<Publication> getAll() {
        return publicationRepository.findAll();
    }

    @Override
    public Publication updatePublication(Long id, Publication publication) {
        Publication existingPublication = publicationRepository.findById(id)
                .orElseThrow(() -> new PublicationNotFoundException(id));

        existingPublication.setTitle(publication.getTitle());
        existingPublication.setMessage(publication.getMessage());
        return publicationRepository.save(existingPublication);
    }

    @Override
    public void deleteById(Long id) {
        Publication existingPublication = publicationRepository.findById(id)
                .orElseThrow(() -> new PublicationNotFoundException(id));
        publicationRepository.delete(existingPublication);
    }
}
