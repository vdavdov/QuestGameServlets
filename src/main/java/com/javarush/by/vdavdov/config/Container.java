package com.javarush.by.vdavdov.config;

import com.javarush.by.vdavdov.model.Quest;
import com.javarush.by.vdavdov.repository.AnswersRepository;
import com.javarush.by.vdavdov.repository.QuestRepository;
import com.javarush.by.vdavdov.repository.Repository;
import com.javarush.by.vdavdov.service.AnswerService;
import com.javarush.by.vdavdov.service.GameService;
import com.javarush.by.vdavdov.service.QuestService;

public class Container {

    public static final QuestsLoader QUESTS_LOADER = new QuestsLoader();
    public static final Repository<Quest> QUEST_REPOSITORY = new QuestRepository();
    public static final AnswersRepository ANSWERS_REPOSITORY = new AnswersRepository();
    public static final QuestService QUEST_SERVICE = new QuestService(QUEST_REPOSITORY, ANSWERS_REPOSITORY, QUESTS_LOADER);
    public static final AnswerService ANSWER_SERVICE = new AnswerService(ANSWERS_REPOSITORY);
    public static final GameService GAME_SERVICE = new GameService(ANSWER_SERVICE);
}
