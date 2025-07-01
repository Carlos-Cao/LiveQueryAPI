package com.api.livequery.service;

import com.api.livequery.entity.Questions;
import com.api.livequery.repository.QuestionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionsServiceImpl implements QuestionsService{

    private final QuestionsRepository questionsRepository;

    public QuestionsServiceImpl(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    @Override
    public Questions addQuestion(Questions questions) {
        return questionsRepository.save(questions);
    }

    @Override
    public Optional<Questions> getQuestionById(int questionId) {
        return questionsRepository.findById(questionId);
    }

    @Override
    public void deleteQuestion(int questionId) {
        questionsRepository.deleteById(questionId);
    }

    @Override
    public List<Questions> getAllQuestions() {
        return questionsRepository.findAll();
    }
}
