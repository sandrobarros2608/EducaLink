package com.company.educalink.service;

import com.company.educalink.entity.Comment;
import com.company.educalink.entity.Publication;
import com.company.educalink.entity.Student;
import com.company.educalink.exception.custom.CommentNotFoundException;
import com.company.educalink.exception.custom.PublicationNotFoundException;
import com.company.educalink.exception.custom.StudentNotFoundException;
import com.company.educalink.repository.CommentRepository;
import com.company.educalink.repository.PublicationRepository;
import com.company.educalink.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // ManyToOne
    @Autowired
    private PublicationRepository publicationRepository;

    // ManyToOne
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Comment saveComment(Comment comment) {
        // Obtain the publication
        Publication publication = publicationRepository.findById(comment.getPublication().getId())
                .orElseThrow(() -> new PublicationNotFoundException(comment.getPublication().getId()));

        // Obtain the student
        Student student = studentRepository.findById(comment.getStudent().getId())
                .orElseThrow(() -> new StudentNotFoundException(comment.getPublication().getId()));

        return commentRepository.save(comment);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException(id));
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));

        existingComment.setMessage(comment.getMessage());

        // Obtain the publication
        Publication publication = publicationRepository.findById(comment.getPublication().getId())
                .orElseThrow(() -> new PublicationNotFoundException(comment.getPublication().getId()));

        // Obtain the student
        Student student = studentRepository.findById(comment.getStudent().getId())
                .orElseThrow(() -> new StudentNotFoundException(comment.getPublication().getId()));
        return commentRepository.save(existingComment);
    }

    @Override
    public void deleteById(Long id) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
        commentRepository.delete(existingComment);
    }
}
