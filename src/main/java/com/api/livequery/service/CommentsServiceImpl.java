package com.api.livequery.service;

import com.api.livequery.entity.Comments;
import com.api.livequery.repository.CommentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsServiceImpl implements CommentsService{

    private final CommentsRepository commentsRepository;

    public CommentsServiceImpl(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @Override
    public Comments addComment(Comments comments) {
        return commentsRepository.save(comments);
    }

    @Override
    public Optional<Comments> getCommentById(int commentId) {
        return commentsRepository.findById(commentId);
    }

    @Override
    public void deleteComment(int commentId) {
        commentsRepository.deleteById(commentId);
    }

    @Override
    public List<Comments> getAllComments() {
        return commentsRepository.findAll();
    }
}
