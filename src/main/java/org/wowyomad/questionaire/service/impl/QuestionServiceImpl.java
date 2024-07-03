package org.wowyomad.questionaire.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.wowyomad.questionaire.dto.QuestionDto;
import org.wowyomad.questionaire.dto.QuestionListDto;
import org.wowyomad.questionaire.model.Answer;
import org.wowyomad.questionaire.model.Question;
import org.wowyomad.questionaire.repository.AnswerRepository;
import org.wowyomad.questionaire.repository.QuestionRepository;
import org.wowyomad.questionaire.service.QuestionService;
import org.wowyomad.questionaire.utils.events.QuestionsUpdatedEvent;
import org.wowyomad.questionaire.utils.exceptions.QuestionInvalidException;
import org.wowyomad.questionaire.utils.exceptions.QuestionNotFoundException;
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
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Long countQuestions() {
        return questionRepository.count();
    }

    @Override
    public List<QuestionDto> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(questionMapper::mapToDto)
                .toList();
    }

    @Override
    public List<QuestionDto> getAllQuestions(Integer offset, Integer limit) {
        Pageable pageable = PageRequest.of(offset, limit);
        return questionRepository.findAll(pageable).stream()
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

        eventPublisher.publishEvent(new QuestionsUpdatedEvent(this));

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

        eventPublisher.publishEvent(new QuestionsUpdatedEvent(this));

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

        eventPublisher.publishEvent(new QuestionsUpdatedEvent(this));
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
