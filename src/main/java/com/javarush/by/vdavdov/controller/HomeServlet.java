package com.javarush.by.vdavdov.controller;

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

@WebServlet(urlPatterns = HOME_SERVLET)
public class HomeServlet extends HttpServlet {
    private final UserService userService = UserUserServiceImpl.getInstance();
    static final Logger logger = LogManager.getLogger(HomeServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Get to /home from {}", req.getRemoteAddr());

        req.getRequestDispatcher(START_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("Post to /home from {}", req.getRemoteAddr());
        //create session and get address:port
        HttpSession session = req.getSession();
        String address = req.getRemoteAddr() + ":" + req.getRemotePort();

        //take name from form
        String name = req.getParameter("name");

        //Create user in repo
        User newUser = new User(name, address);
        userService.create(newUser);
        logger.info("User create success userName:{}, userId:{}: "
                , newUser.getName()
                , newUser.getId());

        //set attribute in session
        session.setAttribute("address", address);
        session.setAttribute("name", name);
        session.setAttribute("id", newUser.getId());
        session.setAttribute("score", newUser.getScore());

        resp.sendRedirect(QUEST_SERVLET);
    }

}
