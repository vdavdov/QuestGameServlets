package com.javarush.by.vdavdov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(urlPatterns = "/win", name = "WinServlet")
public class WinServlet extends HttpServlet {
    static final Logger logger = LogManager.getLogger(HomeServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Get to /win from {}", req.getRemoteAddr());
        //View win.jsp
        req.getRequestDispatcher("WEB-INF/win.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //when restart remove score
        HttpSession session = req.getSession();
        session.removeAttribute("score");
        logger.info("User's {} score remove success, game restarted", req.getRemoteAddr());
        //redirect to home
        resp.sendRedirect("/home");
    }
}
