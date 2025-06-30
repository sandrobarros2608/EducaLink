package com.company.educalink.service;

import com.company.educalink.entity.Announcement;
import com.company.educalink.exception.custom.AnnouncementNotFoundException;
import com.company.educalink.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;


    @Override
    public Announcement saveCourse(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    @Override
    public List<Announcement> getAll() {
        return announcementRepository.findAll();
    }

    @Override
    public Announcement updateCourse(Long id, Announcement announcement) {
        Announcement existingAnnouncement = announcementRepository.findById(id)
                .orElseThrow(() -> new AnnouncementNotFoundException(id));

        existingAnnouncement.setTitle(announcement.getTitle());
        existingAnnouncement.setMessage(announcement.getMessage());
        return announcementRepository.save(existingAnnouncement);
    }

    @Override
    public void deleteById(Long id) {
        Announcement existingAnnouncement = announcementRepository.findById(id)
                .orElseThrow(() -> new AnnouncementNotFoundException(id));
        announcementRepository.delete(existingAnnouncement);
    }
}
