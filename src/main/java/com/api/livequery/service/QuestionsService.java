package com.api.livequery.service;

import com.api.livequery.entity.Questions;

import java.util.List;
import java.util.Optional;

/**
 * QuestionsService provides methods to manage questions in the system.
 * It includes operations to add, retrieve, delete, and list questions.
 */
public interface QuestionsService {

    /**
     * Adds a new question to the system.
     *
     * @param questions the question to be added
     * @return the added question
     */
    Questions addQuestion(Questions questions);

    /**
     * Retrieves a question by its ID.
     *
     * @param questionId the ID of the question to retrieve
     * @return an Optional containing the found question, or empty if not found
     */
    Optional<Questions> getQuestionById(Integer questionId);

    /**
     * Deletes a question by its ID.
     *
     * @param questionId the ID of the question to delete
     */
    void deleteQuestion(Integer questionId);

    /**
     * Retrieves all questions in the system.
     *
     * @return a list of all questions
     */
    List<Questions> getAllQuestions();
}
