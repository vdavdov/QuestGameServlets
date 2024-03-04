package com.javarush.khmelov.lesson14.controller;

import com.javarush.khmelov.lesson14.config.Winter;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandFinder {

    private final Map<String, Command> dymamicCommandMap = new HashMap<>();

    @SneakyThrows
    public Command resolve(String url) {
        Matcher matcher = Pattern.compile("[a-z-]+").matcher(url);
        if (!matcher.find()) {
            return null;
        }
        String key = matcher.group();
        if (dymamicCommandMap.containsKey(key)) {
            return dymamicCommandMap.get(key);
        }
        String simpleName = convertDotStyleToCamelCase(key);
        String className = "com.javarush.khmelov.lesson14.controller.cmd." + simpleName;
        Class<?> aClass = Class.forName(className);
        Command command = (Command) Winter.find(aClass);
        dymamicCommandMap.put(key, command);
        return command;
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
