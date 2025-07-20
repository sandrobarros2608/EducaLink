package com.company.educalink.service;

import com.company.educalink.constant.EmailConstants;
import com.company.educalink.entity.Grade;
import com.company.educalink.entity.Student;
import com.company.educalink.exception.custom.GradeNotFoundException;
import com.company.educalink.exception.custom.StudentNotFoundException;
import com.company.educalink.repository.GradeRepository;
import com.company.educalink.repository.StudentRepository;
import com.company.educalink.util.EmailTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // ManyToOne
    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public Student saveStudent(Student student) {
        // Obtain the degree
        Grade grade = gradeRepository.findById(student.getGrade().getId())
                .orElseThrow(() -> new GradeNotFoundException(student.getGrade().getId()));

        student.setGrade(grade);

        Map<String, String> placeholders = EmailTemplateUtil.buildPlaceholders(student);

        String htmlTemplate = EmailTemplateUtil.loadTemplate("templates/email/EmailRegistrationText.html");

        String formattedText = EmailTemplateUtil.formatRegisterName(
                htmlTemplate,
                placeholders
        );
        emailService.sendHtmlMessage(student.getEmail(), EmailConstants.EMAIL_REGISTRATION_SUBJECT, formattedText);

        return studentRepository.save(student);
    }

    @Transactional(readOnly = true)
    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        existingStudent.setName(student.getName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPassword(student.getPassword());
        existingStudent.setPhoneNumber(student.getPhoneNumber());

        // Obtain the degree
        Grade grade = gradeRepository.findById(student.getGrade().getId())
                .orElseThrow(() -> new GradeNotFoundException(student.getGrade().getId()));
        existingStudent.setGrade(grade);
        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteById(Long id) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(existingStudent);
    }
}
