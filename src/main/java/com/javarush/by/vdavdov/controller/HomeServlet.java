package com.javarush.by.vdavdov.controller;

import com.javarush.by.vdavdov.entity.User;
import com.javarush.by.vdavdov.repository.UserRepository;
import com.javarush.by.vdavdov.service.Service;
import com.javarush.by.vdavdov.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.Getter;

import java.io.IOException;

@Getter
@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    private final Service userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/start-page.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        // Get ip and port of newUser
        String address = req.getRemoteAddr() + ":" + req.getRemotePort();
        // Get name of user by submit form
        String name = req.getParameter("name");

        session.setAttribute("address", address);
        session.setAttribute("name", name);
        //Create user in repo
        User newUser = new User(name, address);
        userService.create(newUser);

        session.setAttribute("id", newUser.getId());
        session.setAttribute("score", newUser.getScore());
        resp.sendRedirect("/quest");
    }

}
