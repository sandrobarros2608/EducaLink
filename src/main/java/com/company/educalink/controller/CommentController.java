package com.company.educalink.controller;

import com.company.educalink.entity.Comment;
import com.company.educalink.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller in charge of managing Comments. Allows you to create, consult and delete comments associated with students.
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Tag(name = "Comments", description = "Endpoints for managing comments")
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    /**
     * Exposing the service methods through dependency injection for use in other layers of the application.
     */
    @Autowired
    private CommentService commentService;

    /**
     * Create comment. Before a comment can be created, a student must exist.
     *
     * @param comment
     * @return Response 201 returning the created object.
     */
    @Operation(summary = "Create comment.", description = "Before a comment can be created, a student must exist.")
    @PostMapping
    public ResponseEntity<Comment> saveComment(@Valid @RequestBody Comment comment) {
        Comment savedComment = commentService.saveComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }

    /**
     * Get comment by id. You can obtain a specific comment.
     *
     * @param id
     * @return Response 200 returning the object.
     */
    @Operation(summary = "Get comment by id.", description = "You can obtain a specific comment.")
    @GetMapping("/{id}")
    public ResponseEntity<Comment> findById(@PathVariable Long id) {
        Comment commentId = commentService.findById(id);
        return ResponseEntity.ok(commentId);
    }

    /**
     * Get list of comments. Gets all comments without pagination.
     *
     * @return Response 200 returning a list of comment.
     */
    @Operation(summary = "Get list of comments.", description = "Gets all comments without pagination.")
    @GetMapping
    public ResponseEntity<List<Comment>> getAll() {
        List<Comment> commentList = commentService.getAll();
        return ResponseEntity.ok(commentList);
    }

    /**
     * Update comment. The comments can be updated without any problem.
     *
     * @param id
     * @param comment
     * @return Response 200 returning the updated comment.
     */
    @Operation(summary = "Update comment.", description = "The comments can be updated without any problem.")
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @Valid @RequestBody Comment comment) {
        Comment updatedComment = commentService.updateComment(id, comment);
        return ResponseEntity.ok(updatedComment);
    }

    /**
     * Delete comment. You can delete comment without any problem.
     *
     * @param id
     * @return Response 204 returning nothing.
     */
    @Operation(summary = "Delete comment.", description = "You can delete comment without any problem.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        commentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
