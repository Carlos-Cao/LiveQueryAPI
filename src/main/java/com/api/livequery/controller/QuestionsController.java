package com.api.livequery.controller;

import com.api.livequery.entity.Questions;
import com.api.livequery.service.QuestionsServiceImpl;
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
public class QuestionsController {

    private final QuestionsServiceImpl questionsService;

    public QuestionsController(QuestionsServiceImpl questionsService) {
        this.questionsService = questionsService;
    }

    @PostMapping(path = "/questions", produces = "application/json")
    public ResponseEntity<Questions> postQuestion(@Valid @RequestBody Questions question) {
        Questions saved = questionsService.addQuestion(question);
        log.info("Creating new question: {}", question);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping(path = "/questions/{questionId}", produces = "application/json")
    public ResponseEntity<Questions> getQuestionById(@PathVariable Integer questionId) {
        Optional<Questions> question = questionsService.getQuestionById(questionId);
        if (question.isPresent()) {
            log.info("Fetching question with ID: {}", questionId);
            return ResponseEntity.ok(question.get());
        } else {
            log.warn("Question with ID: {} not found", questionId);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/questions/{questionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuestionById(@PathVariable Integer questionId) {
        questionsService.deleteQuestion(questionId);
        log.info("Deleting question with ID: {}", questionId);
    }

    @GetMapping(path = "/questions/all", produces = "application/json")
    public List<Questions> getQuestions() {
        List<Questions> questions = questionsService.getAllQuestions();
        log.info("Fetched all questions, count: {}", questions.size());
        return questions;
    }
}
