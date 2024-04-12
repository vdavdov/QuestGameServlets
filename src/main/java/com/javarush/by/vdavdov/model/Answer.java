package com.javarush.by.vdavdov.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

    private long questionId;

    private long questId;

    private String lossMessage;

    private String winningMessage;

    private Map<String, Boolean> answerMap = new HashMap<>();

}
