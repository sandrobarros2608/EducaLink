package com.company.educalink.controller;

import com.company.educalink.entity.Comment;
import com.company.educalink.service.GenericService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing {@link Comment} resources.
 * <p>
 * Provides endpoints to create, retrieve, update, and delete comments associated with students and publications.
 * </p>
 *
 * @author Sandro Barros
 * @since 1.0.0
 */
@Tag(name = "Comments", description = "Endpoints for managing comments")
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    /**
     * Injected service to handle comment operations.
     */
    @Autowired
    private GenericService<Comment, Long> genericService;

    /**
     * Creates a new comment.
     * <p>
     * A valid student and publication must exist before a comment can be created.
     * </p>
     *
     * @param comment the comment to create
     * @return HTTP 201 with the created comment
     */
    @Operation(summary = "Create a new comment", description = "Creates a comment associated with an existing student and publication.")
    @PostMapping
    public ResponseEntity<Comment> saveComment(@Valid @RequestBody Comment comment) {
        Comment savedComment = genericService.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }

    /**
     * Retrieves a comment by its ID.
     *
     * @param id the ID of the comment
     * @return HTTP 200 with the found comment
     */
    @Operation(summary = "Retrieve a comment by ID", description = "Fetches a specific comment by its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Comment> findById(@PathVariable Long id) {
        Comment commentId = genericService.findById(id);
        return ResponseEntity.ok(commentId);
    }

    /**
     * Retrieves all comments.
     *
     * @return HTTP 200 with a list of all comments
     */
    @Operation(summary = "Retrieve all comments", description = "Fetches all comments without pagination.")
    @GetMapping
    public ResponseEntity<List<Comment>> getAll() {
        List<Comment> commentList = genericService.getAll();
        return ResponseEntity.ok(commentList);
    }

    /**
     * Updates an existing comment.
     *
     * @param id the ID of the comment to update
     * @param comment the updated comment data
     * @return HTTP 200 with the updated comment
     */
    @Operation(summary = "Update a comment", description = "Updates a specific comment by its ID.")
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @Valid @RequestBody Comment comment) {
        Comment updatedComment = genericService.update(id, comment);
        return ResponseEntity.ok(updatedComment);
    }

    /**
     * Deletes a comment by its ID.
     *
     * @param id the ID of the comment to delete
     * @return HTTP 204 No Content
     */
    @Operation(summary = "Delete a comment", description = "Deletes a specific comment by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        genericService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
