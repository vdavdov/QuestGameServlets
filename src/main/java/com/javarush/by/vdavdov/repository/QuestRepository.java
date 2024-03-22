package com.javarush.by.vdavdov.repository;

import java.util.List;

public interface QuestRepository {
    String getQuestion(int score);
    List<String> getAnswers(int score);
}
