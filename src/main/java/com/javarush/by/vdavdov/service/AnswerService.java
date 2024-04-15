package com.javarush.by.vdavdov.service;

import com.javarush.by.vdavdov.model.Answer;
import com.javarush.by.vdavdov.repository.AnswersRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AnswerService {

    private final AnswersRepository answersRepository;

    public void create(long questId, Answer answers) {
        answersRepository.create(questId, answers);
    }

    public Answer getAnswer(long questId, long questionId) {
        return answersRepository.getAnswers(questId, questionId);
    }

    public List<Answer> getAll(long questId) {
        return answersRepository.getAll(questId);
    }

    public void update(long questId, long questionId, Answer answers) {
        answersRepository.update(questId, questionId, answers);
    }

    public void remove(long questId, long questionId) {
        answersRepository.remove(questId, questionId);
    }
}
