package com.api.livequery.controller;

import com.api.livequery.entity.Comments;
import com.api.livequery.service.CommentsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Comments> postComments(@RequestBody Comments comments) {
        commentsService.addComment(comments);
        log.info("Creating new comment: {}", comments);
        return ResponseEntity.status(HttpStatus.CREATED).body(comments);
    }

    @GetMapping(path = "/comments/{commentsId}", produces = "application/json")
    public ResponseEntity<Comments> getCommentById(@PathVariable(value = "commentsId") String commentsId) {
        Optional<Comments> comment = commentsService.getCommentById(Integer.parseInt(commentsId));
        if (comment.isPresent()) {
            log.info("Fetching comment with ID: {}", commentsId);
            return ResponseEntity.ok(comment.get());
        } else {
            log.warn("Comment with ID: {} not found", commentsId);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/comments/{commentsId}", produces = "application/json")
    public ResponseEntity<Void> deleteCommentById(@PathVariable(value = "commentsId") String commentsId) {
        commentsService.deleteComment(Integer.parseInt(commentsId));
        log.info("Deleting comment with ID: {}", commentsId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/comments/all", produces = "application/json")
    public List<Comments> getComments() {
        log.info("Fetching all comments");
        return commentsService.getAllComments();
    }
}