package com.javarush.by.vdavdov.controller;


import com.javarush.by.vdavdov.config.Container;
import com.javarush.by.vdavdov.constant.Constants;
import com.javarush.by.vdavdov.model.Quest;
import com.javarush.by.vdavdov.service.QuestService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

@WebServlet(Constants.GO_QUESTS)
public class QuestsServlet extends HttpServlet {

    private final QuestService questService = Container.QUEST_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Quest> quests = questService.getAll();

        req.setAttribute(Constants.QUESTS, quests);

        String pathToJsp = Constants.PATH_TO_JSP.formatted(Constants.GO_QUEST_LIST);
        req.getRequestDispatcher(pathToJsp).forward(req, resp);
    }

}