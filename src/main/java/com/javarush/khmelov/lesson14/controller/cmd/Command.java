package com.javarush.khmelov.lesson14.controller.cmd;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Command {

    default String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return getJspView();
    }

    default String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return getPage();
    }

    default String getJspView() {
        String page = getPage();
        return "/WEB-INF/" + page + ".jsp";
    }

    private String getPage() {
        String snakeName = this.getClass().getSimpleName().chars()
                .mapToObj(s -> String.valueOf((char) s))
                .flatMap(s -> s.matches("[A-Z]")
                        ? Stream.of("-", s)
                        : Stream.of(s))
                .collect(Collectors.joining())
                .toLowerCase();
        return snakeName.startsWith("-")
                ? snakeName.substring(1)
                : snakeName;
    }

}