package org.wowyomad.questionaire.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wowyomad.questionaire.dto.QuestionDto;
import org.wowyomad.questionaire.model.Answer;
import org.wowyomad.questionaire.model.Question;
import org.wowyomad.questionaire.repository.AnswerRepository;
import org.wowyomad.questionaire.repository.QuestionRepository;
import org.wowyomad.questionaire.service.QuestionService;
import org.wowyomad.questionaire.utils.mappers.QuestionMapper;
import org.wowyomad.questionaire.utils.validation.QuestionsValidator;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    private final QuestionMapper questionMapper;

    private final QuestionsValidator questionsValidator;


    @Override
    public List<QuestionDto> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(questionMapper::mapToDto)
                .toList();
    }

    @Override
    public void saveQuestion(QuestionDto questionDto) throws IllegalArgumentException {
        if (!questionsValidator.validate(questionDto)) {
            throw new IllegalArgumentException("Not valid question provided");
        }
        Question question = questionMapper.mapToEntity(questionDto);

        questionRepository.save(question);

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
        List<Answer> answers = answerRepository.findByQuestionId(id);
        try {
            answerRepository.deleteAll(answers);
            questionRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Question not found with id %d", id));
        }
    }

    @Override
    public QuestionDto getQuestion(int id) throws IllegalArgumentException {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Question not found with id %d", id))
                );
        return questionMapper.mapToDto(question);
    }
}
