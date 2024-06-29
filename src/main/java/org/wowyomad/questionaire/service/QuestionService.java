package org.wowyomad.questionaire.service;

import org.wowyomad.questionaire.dto.QuestionDto;
import org.wowyomad.questionaire.dto.QuestionListDto;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> getAllQuestions();
    QuestionDto saveQuestion(QuestionDto questionDto) throws IllegalArgumentException;
    QuestionDto updateQuestion(int id, QuestionDto questionDto) throws IllegalArgumentException;
    void deleteQuestion(int id) throws IllegalArgumentException;
    QuestionDto getQuestion(int id);

    void saveAllQuestions(QuestionListDto questionListDto);
}
