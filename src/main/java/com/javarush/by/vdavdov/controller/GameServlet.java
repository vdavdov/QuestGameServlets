package com.javarush.by.vdavdov.controller;

import com.javarush.by.vdavdov.config.Container;
import com.javarush.by.vdavdov.constant.Constants;
import com.javarush.by.vdavdov.model.Answer;
import com.javarush.by.vdavdov.model.Quest;
import com.javarush.by.vdavdov.service.AnswerService;
import com.javarush.by.vdavdov.service.GameService;
import com.javarush.by.vdavdov.service.QuestService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.javarush.by.vdavdov.config.Container.*;

@WebServlet(Constants.GO_GAME)
public class GameServlet extends HttpServlet {

    private final QuestService questService = QUEST_SERVICE;
    private final GameService gameService = GAME_SERVICE;
    private final AnswerService answerService = ANSWER_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long questId = Long.parseLong(req.getParameter(Constants.QUEST_ID));
        long questionId = Long.parseLong(req.getParameter(Constants.QUESTION_ID));
        boolean answerValue = Boolean.parseBoolean(req.getParameter(Constants.ANSWER_VALUE_FROM_JSP));

        String resultMessage = gameService.checkResultMessage(answerValue, questId, questionId);
        Quest quest = questService.get(questId);
        String question = gameService.getQuestion(quest, questionId);
        Answer answers = answerService.getAnswer(questId, questionId);

        req.setAttribute(Constants.QUESTION, question);
        req.setAttribute(Constants.ANSWERS, answers);
        req.setAttribute(Constants.RESULT_MESSAGE, resultMessage);

        String pathToJsp = Constants.PATH_TO_JSP.formatted(Constants.GO_PLAY_GAME);
        req.getRequestDispatcher(pathToJsp).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean answerValue = Boolean.parseBoolean(req.getParameter(Constants.ANSWER_VALUE));
        long questId = Long.parseLong(req.getParameter(Constants.QUEST_ID));
        long questionId = Long.parseLong(req.getParameter(Constants.QUESTION_ID));

        String pathToJsp = gameService.nextQuestion(answerValue, questId, questionId);

        resp.sendRedirect(pathToJsp);
    }
}