package com.api.livequery.controller;

import com.api.livequery.entity.Questions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/${api.version}")
public class QuestionsController {

    @PostMapping(path = "/questions", produces = "application/json")
    public ResponseEntity<Questions> postQuestion(@RequestBody Questions question) {
        log.info("Creating new question: {}", question);
        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }

    @GetMapping(path = "/questions/{questionId}", produces = "application/json")
    public ResponseEntity<Questions> getQuestionById(@PathVariable(value = "questionId") String questionId) {
        log.info("Fetching question with ID: {}", questionId);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping(path = "/questions/{questionId}", produces = "application/json")
    public ResponseEntity<Void> deleteQuestionById(@PathVariable("questionId") String questionId) {
        log.info("Deleting question with ID: {}", questionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/questions/all", produces = "application/json")
    public ResponseEntity<List<Questions>> getQuestions() {
        log.info("Fetching all questions");
        return ResponseEntity.ok(List.of());
    }
}
