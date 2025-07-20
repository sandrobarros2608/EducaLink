package com.company.educalink.service;

import com.company.educalink.entity.Publication;
import com.company.educalink.entity.Teacher;
import com.company.educalink.exception.custom.PublicationNotFoundException;
import com.company.educalink.exception.custom.TeacherNotFoundException;
import com.company.educalink.repository.PublicationRepository;
import com.company.educalink.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    // ManyToOne
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Publication savePublication(Publication publication) {
        // Obtain the teacher
        Teacher teacher = teacherRepository.findById(publication.getTeacher().getId())
                .orElseThrow(() -> new TeacherNotFoundException(publication.getTeacher().getId()));

        publication.setTeacher(teacher);
        return publicationRepository.save(publication);
    }

    @Transactional(readOnly = true)
    @Override
    public Publication findById(Long id) {
        return publicationRepository.findById(id).orElseThrow(() -> new PublicationNotFoundException(id));
    }

    @Transactional(readOnly = true)
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

        // Obtain the teacher
        Teacher teacher = teacherRepository.findById(publication.getTeacher().getId())
                .orElseThrow(() -> new TeacherNotFoundException(publication.getTeacher().getId()));
        existingPublication.setTeacher(teacher);
        return publicationRepository.save(existingPublication);
    }

    @Override
    public void deleteById(Long id) {
        Publication existingPublication = publicationRepository.findById(id)
                .orElseThrow(() -> new PublicationNotFoundException(id));
        publicationRepository.delete(existingPublication);
    }
}
