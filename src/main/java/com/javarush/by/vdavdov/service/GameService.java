package com.javarush.by.vdavdov.service;

import com.javarush.by.vdavdov.constant.Constants;
import lombok.AllArgsConstructor;
import com.javarush.by.vdavdov.model.Answer;
import com.javarush.by.vdavdov.model.Quest;

@AllArgsConstructor
public class GameService {

    private final AnswerService answerService;

    public String getQuestion(Quest quest, long questionId) {
        return quest.getQuestionsList().get((int) questionId);
    }

    public String nextQuestion(boolean answerValue, long questId, long questionId) {
        long nextQuestionId = checkAnswer(answerValue, questId, questionId);
        return nextQuestionId > 0
                ? Constants.SEND_PATH_TO_NEXT_QUESTION.formatted(questId, nextQuestionId, answerValue)
                : Constants.SEND_PATH_TO_NEXT_QUESTION.formatted(questId, questionId, answerValue);
    }

    public String checkResultMessage(boolean answerValue, long questId, long questionId) {
        Answer answer = answerService.getAnswer(questId, questionId);
        String winningMessage = answer.getWinningMessage();
        String lossMessage = answer.getLossMessage();

        return answerValue
                ? winningMessage
                : lossMessage;
    }

    private long checkAnswer(boolean answerValue, long questId, long questionId) {
        Answer answer = answerService.getAnswer(questId, questionId);
        String winningMassage = answer.getWinningMessage();
        if (answerValue && winningMassage.isBlank()) {
            return ++questionId;
        } else {
            return answerValue
                    ? questionId
                    : -1L;
        }
    }
}