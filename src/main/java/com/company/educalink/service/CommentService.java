package com.company.educalink.service;

import com.company.educalink.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment saveComment(Comment comment);
    List<Comment> getAll();
    Comment updateComment(Long id, Comment comment);
    void deleteById(Long id);
}
