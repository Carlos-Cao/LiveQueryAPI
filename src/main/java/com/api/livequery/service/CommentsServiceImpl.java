package com.api.livequery.service;

import com.api.livequery.entity.Comments;
import com.api.livequery.repository.CommentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsServiceImpl implements CommentsService{

    private final CommentsRepository commentsRepository;

    public CommentsServiceImpl(final CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @Override
    @Transactional
    public Comments addComment(final Comments comments) {
        return commentsRepository.save(comments);
    }

    @Override
    public Optional<Comments> getCommentById(final Integer commentId) {
        return commentsRepository.findById(commentId);
    }

    @Override
    @Transactional
    public void deleteComment(final Integer commentId) {
        commentsRepository.deleteById(commentId);
    }

    @Override
    public List<Comments> getAllComments() {
        return commentsRepository.findAll();
    }
}
