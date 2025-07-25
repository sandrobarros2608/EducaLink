package com.company.educalink.service.impl;

import com.company.educalink.entity.Comment;
import com.company.educalink.entity.Publication;
import com.company.educalink.entity.Student;
import com.company.educalink.exception.custom.ResourceNotFoundException;
import com.company.educalink.repository.CommentRepository;
import com.company.educalink.repository.PublicationRepository;
import com.company.educalink.repository.StudentRepository;
import com.company.educalink.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service implementation for managing {@link Comment} entities.
 * <p>
 * Handles the business logic related to comments, including CRUD operations and
 * the association with a {@link Publication} and a {@link Student}.
 * </p>
 *
 * Implements the generic {@link GenericService} interface.
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Service
public class CommentServiceImpl implements GenericService<Comment, Long> {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private StudentRepository studentRepository;

    /**
     * Saves a new comment after validating the existence of the associated
     * publication and student.
     *
     * @param comment the comment to be saved
     * @return the saved comment
     * @throws ResourceNotFoundException if the publication or student does not exist
     */
    @Override
    public Comment save(Comment comment) {
        Publication publication = publicationRepository.findById(comment.getPublication().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Publication.class, comment.getPublication().getId()));

        Student student = studentRepository.findById(comment.getStudent().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Student.class, comment.getStudent().getId()));

        return commentRepository.save(comment);
    }

    /**
     * Finds a comment by its ID.
     *
     * @param id the ID of the comment
     * @return the found comment
     * @throws ResourceNotFoundException if the comment is not found
     */
    @Transactional(readOnly = true)
    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Comment.class, id));
    }

    /**
     * Retrieves all comments from the database.
     *
     * @return a list of all comments
     */
    @Transactional(readOnly = true)
    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    /**
     * Updates an existing comment by its ID.
     * <p>
     * Also validates and updates its associations with publication and student.
     * </p>
     *
     * @param id the ID of the comment to update
     * @param comment the updated comment data
     * @return the updated comment
     * @throws ResourceNotFoundException if the comment, publication or student is not found
     */
    @Override
    public Comment update(Long id, Comment comment) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Comment.class, id));

        existingComment.setMessage(comment.getMessage());

        // Obtain the publication
        Publication publication = publicationRepository.findById(comment.getPublication().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Publication.class, comment.getPublication().getId()));

        // Obtain the student
        Student student = studentRepository.findById(comment.getStudent().getId())
                .orElseThrow(() -> new ResourceNotFoundException(Student.class, comment.getStudent().getId()));
        return commentRepository.save(existingComment);
    }

    /**
     * Deletes a comment by its ID.
     *
     * @param id the ID of the comment to delete
     * @throws ResourceNotFoundException if the comment is not found
     */
    @Override
    public void deleteById(Long id) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Comment.class, id));
        commentRepository.delete(existingComment);
    }
}
