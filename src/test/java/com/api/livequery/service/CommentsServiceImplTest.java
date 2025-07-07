package com.api.livequery.service;

import com.api.livequery.entity.Comments;
import com.api.livequery.repository.CommentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CommentsServiceImplTest {

    private CommentsRepository commentsRepository;

    private CommentsServiceImpl commentsServiceImpl;

    @BeforeEach
    void setUp() {
        commentsRepository = mock(CommentsRepository.class);
        commentsServiceImpl = new CommentsServiceImpl(commentsRepository);
    }

    @Test
    @DisplayName("Test addComment success.")
    void addComments_success() {

        Comments comment = new Comments();
        when(commentsRepository.save(comment)).thenReturn(comment);

        Comments result = commentsServiceImpl.addComment(comment);

        assertEquals(comment, result);
        verify(commentsRepository).save(comment);
    }

    @Test
    @DisplayName("Test getCommentById success.")
    void getCommentsById_success() {

        Comments comment = new Comments();
        when(commentsRepository.findById(1)).thenReturn(java.util.Optional.of(comment));

        java.util.Optional<Comments> result = commentsServiceImpl.getCommentById(1);

        assertTrue(result.isPresent());
        assertEquals(comment, result.get());
        verify(commentsRepository).findById(1);
    }

    @Test
    @DisplayName("Test getCommentById failure.")
    void getCommentsByQuestionId_failure() {

        when(commentsRepository.findById(2)).thenReturn(Optional.empty());

        Optional<Comments> result = commentsServiceImpl.getCommentById(2);

        assertFalse(result.isPresent());
        verify(commentsRepository).findById(2);
    }

    @Test
    @DisplayName("Test getCommentsByQuestionId success.")
    void deleteQuestion_success() {

        commentsServiceImpl.deleteComment(3);

        verify(commentsRepository).deleteById(3);
    }

    @Test
    @DisplayName("Test getAllComments success.")
    void getAllComments_success() {

        List<Comments> commentsList = List.of(new Comments(), new Comments());
        when(commentsRepository.findAll()).thenReturn(commentsList);

        List<Comments> result = commentsServiceImpl.getAllComments();

        assertEquals(2, result.size());
        verify(commentsRepository).findAll();
    }
}
