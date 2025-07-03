package com.api.livequery.service;

import com.api.livequery.entity.Questions;
import com.api.livequery.repository.QuestionsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionsServiceImpl implements QuestionsService{

    private final QuestionsRepository questionsRepository;

    public QuestionsServiceImpl(final QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    @Override
    @Transactional
    public Questions addQuestion(final Questions questions) {
        return questionsRepository.save(questions);
    }

    @Override
    public Optional<Questions> getQuestionById(final Integer questionId) {
        return questionsRepository.findById(questionId);
    }

    @Override
    @Transactional
    public void deleteQuestion(final Integer questionId) {
        questionsRepository.deleteById(questionId);
    }

    @Override
    public List<Questions> getAllQuestions() {
        return questionsRepository.findAll();
    }
}
