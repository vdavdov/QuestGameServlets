package com.javarush.by.vdavdov.repository;

import com.javarush.by.vdavdov.constant.Constants;
import com.javarush.by.vdavdov.exception.AppException;
import com.javarush.by.vdavdov.model.Answer;
import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
public class AnswersRepository {

    private final Map<Long, List<Answer>> answerMap = new ConcurrentHashMap<>();

    public void create(long questId, Answer answers) {
        List<Answer> answersList = answerMap.get(questId);
        if (Objects.isNull(answersList)) {
            answersList = new ArrayList<>();
        }
        answersList.add(answers);
        answerMap.put(questId, answersList);
    }

    public Answer getAnswers(long questId, long questionId) {
        List<Answer> answersList = getAnswersList(questId);
        Optional<Answer> answerOptional = answersList.stream()
                .filter(a -> a.getQuestionId() == questionId)
                .findAny();
        if (answerOptional.isEmpty()) {
            log.error(Constants.WRONG_DATA);
            throw new AppException(Constants.WRONG_DATA);
        }
        return answerOptional.get();
    }

    public List<Answer> getAll(long questId) {
        return answerMap.get(questId);
    }

    public void update(long questId, long questionId, Answer answers) {
        List<Answer> answersList = getAnswersList(questId);
        remove(questId, questionId);
        answersList.add(answers);
        answerMap.put(questId, answersList);
    }

    public void remove(long questId, long questionId) {
        Answer answerToDelete = getAnswers(questId, questionId);
        List<Answer> answersList = answerMap.get(questId);
        answersList.remove(answerToDelete);
    }

    private List<Answer> getAnswersList(long questId) {
        List<Answer> answersList = answerMap.get(questId);
        if (Objects.isNull(answersList)) {
            log.error(Constants.NO_QUEST_FOR_LOGGER, questId);
            throw new AppException(Constants.NO_QUEST.formatted(questId));
        }
        return answersList;
    }
}
