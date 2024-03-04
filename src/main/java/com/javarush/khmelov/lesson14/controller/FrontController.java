package com.javarush.khmelov.lesson14.controller;

import com.javarush.khmelov.lesson14.config.Winter;
import com.javarush.khmelov.lesson14.controller.cmd.Command;
import com.javarush.khmelov.lesson14.entity.Role;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"", "/home", "/edit-user", "/list-user"})
public class FrontController extends HttpServlet {

    private HttpCommandResolver httpCommandResolver;

    @Override
    public void init(ServletConfig config) throws ServletException {
        config.getServletContext().setAttribute("roles", Role.values());
        httpCommandResolver = Winter.find(HttpCommandResolver.class);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        Command command = httpCommandResolver.resolve(uri);
        if (req.getMethod().equalsIgnoreCase("get")) {
            String view = command.doGet(req, resp);
            req.getRequestDispatcher(view).forward(req, resp);
        } else if (req.getMethod().equalsIgnoreCase("post")) {
            String redirect = command.doPost(req, resp);
            resp.sendRedirect(redirect);
        } else {
            throw new UnsupportedOperationException("incorrect method");
        }
    }
}
