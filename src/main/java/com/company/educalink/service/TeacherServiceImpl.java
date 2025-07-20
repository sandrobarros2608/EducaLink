package com.company.educalink.service;

import com.company.educalink.constant.EmailConstants;
import com.company.educalink.entity.Teacher;
import com.company.educalink.exception.custom.TeacherNotFoundException;
import com.company.educalink.repository.TeacherRepository;
import com.company.educalink.util.EmailTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public Teacher saveTeacher(Teacher teacher) {

        Map<String, String> placeholders = EmailTemplateUtil.buildPlaceholders(teacher);

        String htmlTemplate = EmailTemplateUtil.loadTemplate("templates/email/EmailRegistrationText.html");

        String formattedText = EmailTemplateUtil.formatRegisterName(
                htmlTemplate,
                placeholders
        );

        emailService.sendHtmlMessage(teacher.getEmail(), EmailConstants.EMAIL_REGISTRATION_SUBJECT, formattedText);

        return teacherRepository.save(teacher);
    }

    @Transactional(readOnly = true)
    @Override
    public Teacher findById(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher updateTeacher(Long id, Teacher teacher) {
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(id));

        existingTeacher.setName(teacher.getName());
        existingTeacher.setLastName(teacher.getLastName());
        existingTeacher.setEmail(teacher.getEmail());
        existingTeacher.setPassword(teacher.getPassword());
        existingTeacher.setPhoneNumber(teacher.getPhoneNumber());
        return teacherRepository.save(existingTeacher);
    }

    @Override
    public void deleteById(Long id) {
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new TeacherNotFoundException(id));
        teacherRepository.delete(existingTeacher);
    }
}
