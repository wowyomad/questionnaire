package org.wowyomad.questionaire.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wowyomad.questionaire.dto.AnswerCreateDTO;
import org.wowyomad.questionaire.dto.AnswerResponseDTO;
import org.wowyomad.questionaire.map.AnswerMapper;
import org.wowyomad.questionaire.model.Answer;
import org.wowyomad.questionaire.model.Option;
import org.wowyomad.questionaire.model.Question;
import org.wowyomad.questionaire.repository.AnswerRepository;
import org.wowyomad.questionaire.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerMapper answerMapper;

    public List<AnswerResponseDTO> getAllAnswers() {
        return answerRepository.findAll().stream()
                .map(answerMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public AnswerResponseDTO getAnswerById(Long id) {
        return answerRepository.findById(id)
                .map(answerMapper::toResponseDTO)
                .orElse(null);
    }

    public AnswerResponseDTO saveAnswer(AnswerCreateDTO answerCreateDTO) {
        Question question = questionRepository.findById(answerCreateDTO.getQuestionId())
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));
        Answer answer = answerMapper.fromCreateDTO(answerCreateDTO);
        answer.setQuestion(question);
        setAnswerOptions(answer, question, answerCreateDTO.getSelectedOptions());
        Answer savedAnswer = answerRepository.save(answer);
        return answerMapper.toResponseDTO(savedAnswer);
    }

    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

    private void setAnswerOptions(Answer answer, Question question, List<Long> selectedOptionIndices) {
        List<Option> selectedOptions = question.getOptions().stream()
                .filter(option -> selectedOptionIndices.contains(option.getIndex()))
                .toList();
        if (selectedOptions.size() != selectedOptionIndices.size()) {
            throw new IllegalArgumentException("Invalid option indices provided");
        }
        answer.setSelectedOptions(selectedOptions.stream().map(Option::getId).collect(Collectors.toList()));
    }
}
