package com.javarush.by.vdavdov.controller;

import com.javarush.by.vdavdov.constants.Constants;
import com.javarush.by.vdavdov.entity.User;
import com.javarush.by.vdavdov.service.UserService;
import com.javarush.by.vdavdov.service.UserUserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import static com.javarush.by.vdavdov.constants.Constants.*;

@WebServlet(urlPatterns = QUEST_SERVLET)
public class QuestServlet extends HttpServlet {
    private final UserService userService = UserUserServiceImpl.getInstance();
    static final Logger logger = LogManager.getLogger(HomeServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Get to /quest from {}", req.getRemoteAddr());

        req.getRequestDispatcher(QUEST_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Post to /quest from {}", req.getRemoteAddr());
        //Take user from repo
        HttpSession session = req.getSession();
        long id = (long) session.getAttribute("id");
        User user = userService.get(id).get();
        //Get answer type from parameter
        int answer = Integer.parseInt(req.getParameter("answer"));
        // true -> score + 1 -> true -> redirect to win
        //                   -> false -> continue game
        // false -> remove score and redirect to lose

        if (answer == 1) {
            user.nextLevel();
            logger.info("Score user {} upped", req.getRemoteAddr());
            if (user.getScore() > 2) {
                logger.info("User {} win", req.getRemoteAddr());
                resp.sendRedirect(WIN_SERVLET);
            } else {
                session.setAttribute("score", user.getScore());
                resp.sendRedirect(QUEST_SERVLET);
            }
        } else if (answer == 0) {
            logger.info("User {} defeat", req.getRemoteAddr());
            session.removeAttribute("score");
            resp.sendRedirect(LOSE_SERVLET);
        } else {
            resp.sendRedirect(QUEST_SERVLET);
        }
    }
}
