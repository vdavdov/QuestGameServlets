package com.javarush.by.vdavdov.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.by.vdavdov.constant.Constants;
import com.javarush.by.vdavdov.exception.AppException;
import com.javarush.by.vdavdov.model.Answer;
import com.javarush.by.vdavdov.model.Quest;
import com.javarush.by.vdavdov.util.AnswerList;
import com.javarush.by.vdavdov.util.QuestList;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Data
@NoArgsConstructor
@Log4j2
public class QuestsLoader {

    private List<Quest> quests;

    private List<Answer> answers;

    public void yamlMapping() {
        ObjectMapper yamlMapper = new YAMLMapper();
        try {
            String pathToWebInfConfig = Constants.PATH_TO_WEB_INF_CONFIG;
            File pathForQuest = new File(pathToWebInfConfig + Constants.FILE_QUEST_LIST_YAML);
            File pathForAnswers = new File(pathToWebInfConfig + Constants.FILE_ANSWERS_LIST_YAML);

            quests = yamlMapper.readValue(pathForQuest, QuestList.class);
            answers = yamlMapper.readValue(pathForAnswers, AnswerList.class);
        } catch (NullPointerException | IOException e) {
            log.error(e);
            throw new AppException(e);
        }
    }
}
