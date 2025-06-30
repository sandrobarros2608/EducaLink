package com.company.educalink.service;

import com.company.educalink.entity.Comment;
import com.company.educalink.exception.custom.CommentNotFoundException;
import com.company.educalink.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
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
        return commentRepository.save(existingComment);
    }

    @Override
    public void deleteById(Long id) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
        commentRepository.delete(existingComment);
    }
}
