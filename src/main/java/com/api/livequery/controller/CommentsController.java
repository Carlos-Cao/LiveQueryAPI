package com.api.livequery.controller;

import com.api.livequery.entity.Comments;
import com.api.livequery.service.CommentsServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * CommentsController handles HTTP requests related to comments.
 * It provides endpoints to create, retrieve, delete, and list comments.
 */
@RestController
@Slf4j
@RequestMapping("/api/${api.version}")
public class CommentsController {

    private final CommentsServiceImpl commentsService;

    /**
     * Constructor for CommentsController.
     *
     * @param commentsService the service to handle comment operations
     */
    public CommentsController(CommentsServiceImpl commentsService) {
        this.commentsService = commentsService;
    }

    /**
     * Endpoint to create a new comment.
     *
     * @param comment the comment to be created
     * @return ResponseEntity with the created comment and HTTP status 201 (Created)
     */
    @PostMapping(path = "/comments", produces = "application/json")
    public ResponseEntity<Comments> postComments(@Valid @RequestBody Comments comment) {
        Comments saved = commentsService.addComment(comment);
        log.info("Creating new comment: {}", saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Endpoint to retrieve a comment by its ID.
     *
     * @param commentId the ID of the comment to retrieve
     * @return ResponseEntity with the found comment or HTTP status 404 (Not Found) if not found
     */
    @GetMapping(path = "/comments/{commentId}", produces = "application/json")
    public ResponseEntity<Comments> getCommentById(@PathVariable Integer commentId) {
        Optional<Comments> comment = commentsService.getCommentById(commentId);
        if (comment.isPresent()) {
            log.info("Fetching comment with ID: {}", commentId);
            return ResponseEntity.ok(comment.get());
        } else {
            log.warn("Comment with ID: {} not found", commentId);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint to delete a comment by its ID.
     *
     * @param commentId the ID of the comment to delete
     */
    @DeleteMapping(path = "/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentById(@PathVariable Integer commentId) {
        commentsService.deleteComment(commentId);
        log.info("Deleting comment with ID: {}", commentId);
    }

    /**
     * Endpoint to retrieve all comments.
     *
     * @return List of all comments
     */
    @GetMapping(path = "/comments/all", produces = "application/json")
    public List<Comments> getComments() {
        List<Comments> comments = commentsService.getAllComments();
        log.info("Fetched all comments, count: {}", comments.size());
        return comments;
    }
}