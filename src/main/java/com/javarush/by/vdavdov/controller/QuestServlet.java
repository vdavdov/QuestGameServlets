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

import java.io.IOException;

@WebServlet(name = "QuestServlet", urlPatterns = {"/quest/*"})
public class QuestServlet extends HttpServlet {
    private Service userService = new HomeServlet().getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/quest.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int score = (int) session.getAttribute("score") + 1;
        Boolean answer = Boolean.valueOf(req.getParameter("answer"));
        if (answer) {
            if (score > 2) {
                resp.sendRedirect("/win");
            } else {
                session.setAttribute("score", score);
                resp.sendRedirect("/quest");
            }
        } else {
            session.removeAttribute("score");
            resp.sendRedirect("/lose");
        }
    }
}
