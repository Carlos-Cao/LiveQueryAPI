package com.api.livequery.service;

import com.api.livequery.entity.Questions;

import java.util.List;
import java.util.Optional;

public interface QuestionsService {

    Questions addQuestion(Questions questions);

    Optional<Questions> getQuestionById(Integer questionId);

    void deleteQuestion(Integer questionId);

    List<Questions> getAllQuestions();
}
