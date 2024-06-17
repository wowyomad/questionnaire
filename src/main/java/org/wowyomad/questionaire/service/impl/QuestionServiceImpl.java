package org.wowyomad.questionaire.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.wowyomad.questionaire.dto.QuestionDto;
import org.wowyomad.questionaire.model.Question;
import org.wowyomad.questionaire.repository.QuestionRepository;
import org.wowyomad.questionaire.service.QuestionService;
import org.wowyomad.questionaire.utils.mappers.QuestionMapper;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private final QuestionMapper questionMapper;

    @Override
    public List<QuestionDto> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(questionMapper::mapToDto)
                .toList();
    }

    @Override
    public void saveQuestion(QuestionDto questionDto) throws IllegalArgumentException{
        Question question = questionMapper.mapToEntity(questionDto);
        try {
            questionRepository.save(question);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Question provided was null");
        }

    }

    @Override
    public void updateQuestion(int id, QuestionDto questionDto) throws IllegalArgumentException {
        Optional<Question> existingQuestion = questionRepository.findById(id);
        if (existingQuestion.isPresent()) {
            Question newQuestion = questionMapper.mapToEntity(questionDto);
            newQuestion.setId(id);
            questionRepository.save(newQuestion);
        } else {
            throw new IllegalArgumentException(String.format("Question not found with id %d", id));
        }
    }

    @Override
    public void deleteQuestion(int id) throws IllegalArgumentException {
        try {
            questionRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Question not found with id %d", id));
        }
    }

    @Override
    public QuestionDto getQuestion(int id) throws IllegalArgumentException{
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Question not found with id %d", id))
                );
        return questionMapper.mapToDto(question);
    }
}
