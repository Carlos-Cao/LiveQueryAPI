package com.api.livequery.controller;

import com.api.livequery.entity.Questions;
import com.api.livequery.service.QuestionsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Questions> postQuestion(@RequestBody Questions question) {
        questionsService.addQuestion(question);
        log.info("Creating new question: {}", question);
        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }

    @GetMapping(path = "/questions/{questionId}", produces = "application/json")
    public ResponseEntity<Questions> getQuestionById(@PathVariable(value = "questionId") String questionId) {
        Optional<Questions> question = questionsService.getQuestionById(Integer.parseInt(questionId));
        if (question.isPresent()) {
            log.info("Fetching question with ID: {}", questionId);
            return ResponseEntity.ok(question.get());
        } else {
            log.warn("Question with ID: {} not found", questionId);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/questions/{questionId}", produces = "application/json")
    public void deleteQuestionById(@PathVariable(value = "questionId") String questionId) {
        questionsService.deleteQuestion(Integer.parseInt(questionId));
        log.info("Deleting question with ID: {}", questionId);
    }

    @GetMapping(path = "/questions/all", produces = "application/json")
    public List<Questions> getQuestions() {
        log.info("Fetching all questions");
        return questionsService.getAllQuestions();
    }
}
