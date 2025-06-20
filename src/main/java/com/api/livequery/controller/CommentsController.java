package com.api.livequery.controller;

import com.api.livequery.entity.Comments;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/${api.version}")
public class CommentsController {

    @PostMapping(path = "/comments", produces = "application/json")
    public ResponseEntity<Comments> postComments(@RequestBody Comments comments) {
        log.info("Creating new comment: {}", comments);
        return ResponseEntity.status(HttpStatus.CREATED).body(comments);
    }

    @GetMapping(path = "/comments/{commentsId}", produces = "application/json")
    public ResponseEntity<Comments> getCommentById(@PathVariable(value = "commentsId") String commentsId) {
        log.info("Fetching comment with ID: {}", commentsId);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping(path = "/comments/{commentsId}", produces = "application/json")
    public ResponseEntity<Void> deleteCommentById(@PathVariable("commentsId") String commentsId) {
        log.info("Deleting comment with ID: {}", commentsId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/comments/all", produces = "application/json")
    public ResponseEntity<List<Comments>> getComments() {
        log.info("Fetching all comments");
        return ResponseEntity.ok(List.of());
    }
}