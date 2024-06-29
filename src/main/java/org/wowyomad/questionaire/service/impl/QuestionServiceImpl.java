package org.wowyomad.questionaire.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wowyomad.questionaire.dto.QuestionDto;
import org.wowyomad.questionaire.dto.QuestionListDto;
import org.wowyomad.questionaire.model.Answer;
import org.wowyomad.questionaire.model.Question;
import org.wowyomad.questionaire.repository.AnswerRepository;
import org.wowyomad.questionaire.repository.QuestionRepository;
import org.wowyomad.questionaire.service.QuestionService;
import org.wowyomad.questionaire.utils.exceptions.QuestionInvalidException;
import org.wowyomad.questionaire.utils.exceptions.QuestionNotFoundException;
import org.wowyomad.questionaire.utils.mappers.QuestionMapper;
import org.wowyomad.questionaire.utils.validation.QuestionsValidator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public QuestionDto saveQuestion(QuestionDto questionDto) throws QuestionInvalidException {
        if (!questionsValidator.validate(questionDto)) {
            throw new QuestionInvalidException("Not valid question provided");
        }
        Question question = questionMapper.mapToEntity(questionDto);
        question = questionRepository.save(question);
        return questionMapper.mapToDto(question);
    }

    @Override
    public QuestionDto updateQuestion(int id, QuestionDto questionDto) throws QuestionNotFoundException, QuestionInvalidException {
        if (!questionsValidator.validate(questionDto)) {
            throw new QuestionInvalidException("Not valid question provided");
        }
        Optional<Question> existingQuestion = questionRepository.findById(id);
        if (existingQuestion.isEmpty()) {
            throw new QuestionNotFoundException(String.format("Question not found with id %d", id));

        }
        Question updatedQuestion = questionMapper.mapToEntity(questionDto);
        updatedQuestion.setId(id);
        updatedQuestion = questionRepository.save(updatedQuestion);
        return questionMapper.mapToDto(updatedQuestion);
    }

    @Override
    public void deleteQuestion(int id) throws QuestionNotFoundException {
        List<Answer> answers = answerRepository.findByQuestionId(id);
        try {
            answerRepository.deleteAll(answers);
            questionRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new QuestionNotFoundException(String.format("Question not found with id %d", id));
        }
    }

    @Override
    public QuestionDto getQuestion(int id) throws QuestionNotFoundException {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException(String.format("Question not found with id %d", id)));
        return questionMapper.mapToDto(question);
    }

    @Override
    public void saveAllQuestions(QuestionListDto questionListDto) {
        List<Question> questions = questionListDto.getQuestions().stream().map(questionDto -> {
            if (!questionsValidator.validate(questionDto)) {
                throw new QuestionInvalidException("Not valid question provided");
            }
            return questionMapper.mapToEntity(questionDto);
        }).toList();
        questionRepository.saveAll(questions);
    }
}
