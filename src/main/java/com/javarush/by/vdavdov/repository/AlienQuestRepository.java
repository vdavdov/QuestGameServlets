package com.javarush.by.vdavdov.repository;

import java.util.ArrayList;
import java.util.List;

public class AlienQuestRepository {
    private final List<String> questions = new ArrayList<>();
    private final List<String> answers = new ArrayList<>();
    public AlienQuestRepository() {
        questions.add("<p>Ты потерял память. <br> Принять вызов НЛО?<p>");
        questions.add("<p>Ты принял вызов. Поднимешься на мостик к капитану?</p>");
        questions.add("<p>Ты поднялся на мостик. Ты кто?</p>");
        answers.add("<p>Принять вызов НЛО</p>");
        answers.add("<p>Отклонить вызов</p>");
        answers.add("<p>Подняться на мостик</p>");
        answers.add("<p>Отказаться подниматься на мостик</p>");
        answers.add("<p>Рассказать правду о себе</p>");
        answers.add("<p>Солгать о себе</p>");
    }
    public List<String> getAnswersList(int score) {
        List<String> answer = new ArrayList<>();
        if (score == 0) {
            answer.add(answers.get(0));
            answer.add(answers.get(1));
        } else if (score == 1) {
            answer.add(answers.get(2));
            answer.add(answers.get(3));
        } else if (score == 2) {
            answer.add(answers.get(4));
            answer.add(answers.get(5));
        } else {
            answer.add("У нас проблемы, вы слишком хороши...Или я плох?");
        }
        return answer;
    }
    public String getQuestionsList(int score) {
        String question;
        if (score == 0) {
            question = questions.get(0);
        } else if (score == 1) {
            question = questions.get(1);
        } else if (score == 2) {
            question = questions.get(2);
        } else {
            question = "У нас проблемы, вы слишком хороши...Или я плох?";
        }
        return question;
    }
}
