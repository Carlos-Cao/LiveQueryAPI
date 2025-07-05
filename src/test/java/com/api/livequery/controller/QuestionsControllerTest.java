package com.api.livequery.controller;

import com.api.livequery.entity.Questions;
import com.api.livequery.service.QuestionsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(QuestionsController.class)
class QuestionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuestionsServiceImpl questionsService;

    @TestConfiguration
    static class MockConfig {
        @Bean
        QuestionsServiceImpl questionsService() {
            return Mockito.mock(QuestionsServiceImpl.class);
        }
    }

    private Questions createSampleQuestion() {
        Questions question = new Questions();
        question.setQuestionId(1);
        question.setName("John Doe");
        question.setQuestion("What is LiveQuery?");
        question.setDescription("I want to know more about LiveQuery and its features.");
        return question;
    }

    @Test
    @DisplayName("Test postQuestion with Created Status.")
    void postQuestion_created() throws Exception {

        Mockito.when(questionsService.addQuestion(any(Questions.class))).thenReturn(createSampleQuestion());

        mockMvc.perform(
                        post("/api/v1/questions")
                                .contentType("application/json")
                                .content("{\"questionId\":1,\"name\":\"John Doe\",\"question\":\"What is LiveQuery?\",\"description\":\"I want to know more about LiveQuery and its features.\"}")                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.questionId").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.question").value("What is LiveQuery?"))
                .andExpect(jsonPath("$.description").value("I want to know more about LiveQuery and its features."));
    }

    @Test
    @DisplayName("Test getQuestionById with Found Status.")
    void getQuestionById_found() throws Exception {

        Mockito.when(questionsService.getQuestionById(1)).thenReturn(Optional.of(createSampleQuestion()));

        mockMvc.perform(
                        get("/api/v1/questions/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.questionId").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.question").value("What is LiveQuery?"))
                .andExpect(jsonPath("$.description").value("I want to know more about LiveQuery and its features."));
    }

    @Test
    @DisplayName("Test getQuestionById with Not Found Status.")
    void getQuestionById_notFound() throws Exception {

        Mockito.when(questionsService.getQuestionById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/questions/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test deleteQuestionById with No Content Status.")
    void deleteQuestionById_noContent() throws Exception {

        mockMvc.perform(delete("/api/v1/questions/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(questionsService).deleteQuestion(1);
    }

    @Test
    @DisplayName("Test getQuestions with Success Status.")
    void getQuestions_success() throws Exception {

        Questions question1 = createSampleQuestion();
        Questions question2 = createSampleQuestion();
        Mockito.when(questionsService.getAllQuestions()).thenReturn(List.of(question1, question2));

        mockMvc.perform(get("/api/v1/questions/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
