package com.javarush.khmelov.lesson14.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface Command {

    default String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return jsp();
    }

    default void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(getUrlPatternFromClass());
    }

    default String jsp() {
        String urlPattern = getUrlPatternFromClass();
        return "/WEB-INF/" + urlPattern + ".jsp";
    }

    private String getUrlPatternFromClass() {
        String snakeName = this.getClass().getSimpleName().chars()
                .flatMap(s -> s <= 'Z' && s >= 'A'
                        ? IntStream.of('-', s)
                        : IntStream.of(s))
                .mapToObj(s -> String.valueOf((char) s)).collect(Collectors.joining());
        return snakeName.startsWith("-") ? snakeName.substring(1) : snakeName;
    }

}