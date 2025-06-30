package com.company.educalink.service;

import com.company.educalink.entity.Announcement;

import java.util.List;

public interface AnnouncementService {

    Announcement saveAnnouncement(Announcement announcement);
    List<Announcement> getAll();
    Announcement updateAnnouncement(Long id, Announcement announcement);
    void deleteById(Long id);
}
