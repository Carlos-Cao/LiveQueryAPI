package com.api.livequery.service;

import com.api.livequery.entity.Comments;

import java.util.List;
import java.util.Optional;

public interface CommentsService {

    Comments addComment(Comments comments);

    Optional<Comments> getCommentById(int commentId);

    void deleteComment(int commentId);

    List<Comments> getAllComments();
}
