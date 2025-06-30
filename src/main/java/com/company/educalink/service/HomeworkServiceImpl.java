package com.company.educalink.service;

import com.company.educalink.entity.Homework;
import com.company.educalink.repository.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    @Autowired
    private HomeworkRepository homeworkRepository;

    @Override
    public Homework saveHomework(Homework homework) {
        return homeworkRepository.save(homework);
    }

    @Override
    public Homework findById(Long id) {
        return homeworkRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Homework not found with id: " + id));
    }

    @Override
    public List<Homework> getAll() {
        return homeworkRepository.findAll();
    }

    @Override
    public Homework updateHomework(Long id, Homework homework) {
        Homework existingHomework = homeworkRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Homework not found with id: " + id));

        existingHomework.setTitle(homework.getTitle());
        existingHomework.setDescription(homework.getDescription());
        existingHomework.setCreationDate(homework.getCreationDate());
        existingHomework.setDueDate(homework.getDueDate());

        return homeworkRepository.save(existingHomework);
    }

    @Override
    public void deleteById(Long id) {
        Homework existingHomework = homeworkRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Homework not found with id: " + id));

        homeworkRepository.delete(existingHomework);
    }
}
