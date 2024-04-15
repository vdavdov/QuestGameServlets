package com.javarush.by.vdavdov.service;

import com.javarush.by.vdavdov.config.QuestsLoader;
import com.javarush.by.vdavdov.model.Answer;
import com.javarush.by.vdavdov.model.Quest;
import com.javarush.by.vdavdov.repository.AnswersRepository;
import com.javarush.by.vdavdov.repository.Repository;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class QuestService {

    private final Repository<Quest> questRepository;

    private final AnswersRepository answersRepository;

    private final QuestsLoader questsLoader;

    public Collection<Quest> getAll() {
        Collection<Quest> quests = questRepository.getAll();
        if (quests.isEmpty()) {
            doUpload();
        }
        return questRepository.getAll();
    }

    public Quest get(long id) {
        return questRepository.get(id);
    }

    public void create(Quest quest) {
        questRepository.create(quest);
    }

    public void update(Quest quest) {
        questRepository.update(quest);
    }

    public void delete(Quest quest) {
        questRepository.delete(quest);
    }

    private void doUpload() {
        questsLoader.yamlMapping();
        List<Quest> questList = questsLoader.getQuests();
        List<Answer> answersList = questsLoader.getAnswers();

        questList.forEach(this::create);
        answersList.forEach(answers -> {
            long questId = answers.getQuestId();
            answersRepository.create(questId, answers);
        });
    }
}
