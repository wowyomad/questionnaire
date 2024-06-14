package org.wowyomad.questionaire.map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wowyomad.questionaire.dto.AnswerCreateDTO;
import org.wowyomad.questionaire.dto.AnswerResponseDTO;
import org.wowyomad.questionaire.dto.OptionResponseDTO;
import org.wowyomad.questionaire.model.Answer;
import org.wowyomad.questionaire.model.Option;
import org.wowyomad.questionaire.model.Question;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnswerMapper {

    @Autowired
    private OptionMapper optionMapper;

    public AnswerResponseDTO toResponseDTO(Answer answer) {
        if (answer == null) {
            return null;
        }
        AnswerResponseDTO dto = new AnswerResponseDTO();
        dto.setId(answer.getId());
        dto.setQuestionId(answer.getQuestion().getId());
        dto.setResponse(answer.getResponse());
        dto.setSelectedOptions(
                answer.getSelectedOptions().stream()
                        .map(optionId -> {
                            Option option = getOptionById(answer.getQuestion(), optionId);
                            return optionMapper.toResponseDTO(option);
                        })
                        .collect(Collectors.toList())
        );
        return dto;
    }

    public Answer fromCreateDTO(AnswerCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        Answer answer = new Answer();
        answer.setResponse(dto.getResponse());
        answer.setSelectedOptions(dto.getSelectedOptions());
        Question question = new Question();
        question.setId(dto.getQuestionId());
        answer.setQuestion(question);
        return answer;
    }

    private Option getOptionById(Question question, Long optionId) {
        return question.getOptions().stream()
                .filter(option -> option.getId().equals(optionId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Option not found with id: " + optionId));
    }
}
