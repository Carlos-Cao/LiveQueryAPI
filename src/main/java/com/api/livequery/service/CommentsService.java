package com.api.livequery.service;

import com.api.livequery.entity.Comments;

import java.util.List;
import java.util.Optional;

public interface CommentsService {

    Comments addComment(Comments comments);

    Optional<Comments> getCommentById(Integer commentId);

    void deleteComment(Integer commentId);

    List<Comments> getAllComments();
}
