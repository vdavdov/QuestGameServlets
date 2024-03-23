package com.javarush.by.vdavdov.controller;

import com.javarush.by.vdavdov.entity.User;
import com.javarush.by.vdavdov.service.Service;
import com.javarush.by.vdavdov.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "QuestServlet", urlPatterns = {"/quest/*"})
public class QuestServlet extends HttpServlet {
    private Service userService = UserService.getInstance();
    static final Logger logger = LogManager.getLogger(HomeServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Get to /quest from {}", req.getRemoteAddr());
        //View quest.jsp
        req.getRequestDispatcher("WEB-INF/quest.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Post to /quest from {}", req.getRemoteAddr());
        //Take user from repo
        HttpSession session = req.getSession();
        long id = (long) session.getAttribute("id");
        User user = userService.get(id).get();
        //Get answer type from parameter
        Boolean answer = Boolean.valueOf(req.getParameter("answer"));
        // true -> score + 1 -> true -> redirect to win
        //                   -> false -> continue game
        // false -> remove score and redirect to lose
        if (answer) {
            user.nextLevel();
            logger.info("Score user {} upped", req.getRemoteAddr());
            if (user.getScore() > 2) {
                logger.info("User {} win", req.getRemoteAddr());
                resp.sendRedirect("/win");
            } else {
                session.setAttribute("score", user.getScore());
                resp.sendRedirect("/quest");
            }
        } else {
            logger.info("User {} defeat", req.getRemoteAddr());
            session.removeAttribute("score");
            resp.sendRedirect("/lose");
        }
    }
}
