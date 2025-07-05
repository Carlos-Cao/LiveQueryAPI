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

@RestController
@Slf4j
@RequestMapping("/api/${api.version}")
public class CommentsController {

    private final CommentsServiceImpl commentsService;

    public CommentsController(CommentsServiceImpl commentsService) {
        this.commentsService = commentsService;
    }

    @PostMapping(path = "/comments", produces = "application/json")
    public ResponseEntity<Comments> postComments(@Valid @RequestBody Comments comment) {
        Comments saved = commentsService.addComment(comment);
        log.info("Creating new comment: {}", saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

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

    @DeleteMapping(path = "/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCommentById(@PathVariable Integer commentId) {
        commentsService.deleteComment(commentId);
        log.info("Deleting comment with ID: {}", commentId);
    }

    @GetMapping(path = "/comments/all", produces = "application/json")
    public List<Comments> getComments() {
        List<Comments> comments = commentsService.getAllComments();
        log.info("Fetched all comments, count: {}", comments.size());
        return comments;
    }
}