package com.javarush.by.vdavdov.controller;

import com.javarush.by.vdavdov.constant.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {
        Constants.GO_EMPTY_SLASH,
        Constants.GO_HOME,
        Constants.GO_IN_DEV},
        smallIcon = Constants.PATH_TO_FAVICON)
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI().equals(Constants.GO_IN_DEV)
                ? Constants.GO_IN_DEV
                : Constants.GO_HOME;
        String pathToJsp = Constants.PATH_TO_JSP.formatted(requestURI.substring(1));
        req.getRequestDispatcher(pathToJsp).forward(req, resp);

    }
}
