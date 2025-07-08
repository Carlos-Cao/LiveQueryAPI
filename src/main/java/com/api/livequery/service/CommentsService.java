package com.api.livequery.service;

import com.api.livequery.entity.Comments;

import java.util.List;
import java.util.Optional;

/**
 * CommentsService provides methods to manage comments in the system.
 * It includes operations to add, retrieve, delete, and list comments.
 */
public interface CommentsService {

    /**
     * Adds a new comment to the system.
     *
     * @param comments the comment to be added
     * @return the added comment
     */
    Comments addComment(Comments comments);

    /**
     * Retrieves a comment by its ID.
     *
     * @param commentId the ID of the comment to retrieve
     * @return an Optional containing the found comment, or empty if not found
     */
    Optional<Comments> getCommentById(Integer commentId);

    /**
     * Deletes a comment by its ID.
     *
     * @param commentId the ID of the comment to delete
     */
    void deleteComment(Integer commentId);

    /**
     * Retrieves all comments in the system.
     *
     * @return a list of all comments
     */
    List<Comments> getAllComments();
}
