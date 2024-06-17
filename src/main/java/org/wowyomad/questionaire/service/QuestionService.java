package org.wowyomad.questionaire.service;

import org.wowyomad.questionaire.dto.QuestionDto;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> getAllQuestions();

    void saveQuestion(QuestionDto questionDto) throws IllegalArgumentException;
    void updateQuestion(int id, QuestionDto questionDto) throws IllegalArgumentException;

    void deleteQuestion(int id) throws IllegalArgumentException;

    QuestionDto getQuestion(int id);
}
