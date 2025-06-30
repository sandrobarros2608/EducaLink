package com.company.educalink.service;

import com.company.educalink.entity.Announcement;
import com.company.educalink.entity.Teacher;
import com.company.educalink.exception.custom.AnnouncementNotFoundException;
import com.company.educalink.exception.custom.TeacherNotFoundException;
import com.company.educalink.repository.AnnouncementRepository;
import com.company.educalink.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    // ManyToOne
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Announcement saveAnnouncement(Announcement announcement) {
        // Obtain the teacher
        Teacher teacher = teacherRepository.findById(announcement.getTeacher().getId())
                .orElseThrow(() -> new TeacherNotFoundException(announcement.getTeacher().getId()));

        announcement.setTeacher(teacher);
        return announcementRepository.save(announcement);
    }

    @Override
    public List<Announcement> getAll() {
        return announcementRepository.findAll();
    }

    @Override
    public Announcement updateAnnouncement(Long id, Announcement announcement) {
        Announcement existingAnnouncement = announcementRepository.findById(id)
                .orElseThrow(() -> new AnnouncementNotFoundException(id));

        existingAnnouncement.setTitle(announcement.getTitle());
        existingAnnouncement.setMessage(announcement.getMessage());

        // Obtain the teacher
        Teacher teacher = teacherRepository.findById(announcement.getTeacher().getId())
                .orElseThrow(() -> new TeacherNotFoundException(announcement.getTeacher().getId()));
        existingAnnouncement.setTeacher(teacher);
        return announcementRepository.save(existingAnnouncement);
    }

    @Override
    public void deleteById(Long id) {
        Announcement existingAnnouncement = announcementRepository.findById(id)
                .orElseThrow(() -> new AnnouncementNotFoundException(id));
        announcementRepository.delete(existingAnnouncement);
    }
}
