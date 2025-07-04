package com.api.livequery.controller;

import com.api.livequery.entity.Comments;
import com.api.livequery.service.CommentsServiceImpl;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentsController.class)
public class CommentsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CommentsServiceImpl commentsService;

    @TestConfiguration
    static class MockConfig {
        @Bean
        CommentsServiceImpl commentsService() {
            return Mockito.mock(CommentsServiceImpl.class);
        }
    }

    private Comments createSampleComment() {
        Comments comment  = new Comments();
        comment.setCommentId(1);
        comment.setName("Jane Doe");
        comment.setComment("Test comment");
        comment.setQuestionId(1);
        return comment;
    }

    @Test
    @DisplayName("Test postComment with Created Status.")
    void postComment_created() throws Exception {

        Mockito.when(commentsService.addComment(any(Comments.class))).thenReturn(createSampleComment());

        mockMvc.perform(
                        post("/api/v1/comments")
                                .contentType("application/json")
                                .content("{\"commentId\":1,\"name\":\"Jane Doe\",\"comment\":\"Test comment\",\"questionId\":\"1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.commentId").value(1))
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.comment").value("Test comment"))
                .andExpect(jsonPath("$.questionId").value(1));
    }

    @Test
    @DisplayName("Test getCommentById with Found Status.")
    void getCommentById_found() throws Exception {

        Mockito.when(commentsService.getCommentById(1)).thenReturn(Optional.of(createSampleComment()));

        mockMvc.perform(
                        get("/api/v1/comments/1")
                                .contentType("application/json")
                                .content("{\"commentId\":1,\"name\":\"Jane Doe\",\"comment\":\"Test comment\",\"questionId\":\"1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentId").value(1))
                .andExpect(jsonPath("$.name").value("Jane Doe"))
                .andExpect(jsonPath("$.comment").value("Test comment"))
                .andExpect(jsonPath("$.questionId").value(1));
    }

    @Test
    @DisplayName("Test getCommentById with Not Found Status.")
    void getCommentById_notFound() throws Exception {

        Mockito.when(commentsService.getCommentById(1)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/comments/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test deleteCommentById with No Content Status.")
    void deleteCommentById_noContent() throws Exception {

        mockMvc.perform(delete("/api/v1/comments/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(commentsService).deleteComment(1);
    }

    @Test
    @DisplayName("Test getComments with Success Status.")
    void getComments_success() throws Exception {

        Comments comment1 = createSampleComment();
        Comments comment2 = createSampleComment();
        Mockito.when(commentsService.getAllComments()).thenReturn(List.of(comment1, comment2));

        mockMvc.perform(get("/api/v1/comments/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
