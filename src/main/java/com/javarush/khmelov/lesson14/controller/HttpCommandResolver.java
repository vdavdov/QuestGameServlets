package com.javarush.khmelov.lesson14.controller;

import com.javarush.khmelov.lesson14.config.Winter;
import com.javarush.khmelov.lesson14.controller.cmd.Command;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpCommandResolver {

    private final Map<String, Command> dymamicCommandMap = new HashMap<>();

    @SneakyThrows
    public Command resolve(String url) {
        Matcher matcher = Pattern.compile("[a-z-]+").matcher(url);
        String key = "index";
        if (matcher.find()) {
            key = matcher.group();
        }
        if (dymamicCommandMap.containsKey(key)) {
            return dymamicCommandMap.get(key);
        }
        String simpleName = convertDotStyleToCamelCase(key);
        Command command = getHttpCommand(simpleName);
        dymamicCommandMap.put(key, command);
        return command;
    }

    public static Command getHttpCommand(String simpleName) throws ClassNotFoundException {
        String className = "com.javarush.khmelov.lesson14.controller.cmd." + simpleName;
        Class<?> aClass = Class.forName(className);
        return (Command) Winter.find(aClass);
    }

    public static String convertDotStyleToCamelCase(String input) {
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;
        for (char c : input.toCharArray()) {
            if (c == '-') {
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    result.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    result.append(Character.toLowerCase(c));
                }
            }
        }
        return result.toString();
    }
}
