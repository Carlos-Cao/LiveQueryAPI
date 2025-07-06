package com.api.livequery.service;

import com.api.livequery.entity.Questions;
import com.api.livequery.repository.QuestionsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class QuestionsServiceImplTest {

    private QuestionsRepository questionsRepository;

    private QuestionsServiceImpl questionsServiceImpl;

    @BeforeEach
    void setUp() {
        questionsRepository = mock(QuestionsRepository.class);
        questionsServiceImpl = new QuestionsServiceImpl(questionsRepository);
    }

    @Test
    @DisplayName("Test addQuestion success.")
    void addQuestion_success() {

        Questions question = new Questions();
        when(questionsRepository.save(question)).thenReturn(question);

        Questions result = questionsServiceImpl.addQuestion(question);

        assertEquals(question, result);
        verify(questionsRepository).save(question);
    }

    @Test
    @DisplayName("Test getQuestionById success.")
    void getQuestionById_success() {

        Questions question = new Questions();
        when(questionsRepository.findById(1)).thenReturn(java.util.Optional.of(question));

        Optional<Questions> result = questionsServiceImpl.getQuestionById(1);

        assertTrue(result.isPresent());
        assertEquals(question, result.get());
        verify(questionsRepository).findById(1);
    }

    @Test
    @DisplayName("Test getQuestionById failure.")
    void getQuestionById_failure() {

        when(questionsRepository.findById(2)).thenReturn(Optional.empty());

        Optional<Questions> result = questionsServiceImpl.getQuestionById(2);

        assertFalse(result.isPresent());
        verify(questionsRepository).findById(2);
    }

    @Test
    @DisplayName("Test deleteQuestion success.")
    void deleteQuestion_success() {

        questionsServiceImpl.deleteQuestion(3);

        verify(questionsRepository).deleteById(3);
    }

    @Test
    @DisplayName("Test getAllQuestions success.")
    void getAllQuestions_success() {

        List<Questions> questionsList = List.of(new Questions(), new Questions());
        when(questionsRepository.findAll()).thenReturn(questionsList);

        List<Questions> result = questionsServiceImpl.getAllQuestions();

        assertEquals(2, result.size());
        verify(questionsRepository).findAll();
    }
}
