package org.wowyomad.questionaire.utils.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.wowyomad.questionaire.dto.AnswerDto;
import org.wowyomad.questionaire.dto.OptionDto;
import org.wowyomad.questionaire.model.Answer;
import org.wowyomad.questionaire.model.Option;
import org.wowyomad.questionaire.model.Question;
import org.wowyomad.questionaire.model.Submission;
import org.wowyomad.questionaire.utils.exceptions.AnswerInvalidException;

import java.util.List;

@Component
@AllArgsConstructor
public class AnswerMapper {
    private final OptionMapper optionMapper;

    public AnswerDto mapToDto(Answer answer) {
        List<OptionDto> selectedOptionsDTO = null;
        if(answer.getSelectedOptions() != null) {
            selectedOptionsDTO = answer.getSelectedOptions().stream()
                    .map(optionMapper::mapToDto)
                    .toList();

        }
        return new AnswerDto(
                answer.getId(),
                answer.getQuestion().getId(),
                answer.getQuestion().getType().name(),
                answer.getText(),
                selectedOptionsDTO
        );
    }

    public Answer mapToEntity(AnswerDto answerDto, Question question, Submission submission) throws AnswerInvalidException {
        if (!question.getId().equals(answerDto.getQuestionId())) {
            throw new AnswerInvalidException("Question ID mismatch");
        }

        List<Option> selectedOptions;
        if (answerDto.getSelectedOptions() != null) {
            selectedOptions = answerDto.getSelectedOptions().stream()
                    .map(optionDto -> optionMapper.mapToEntity(optionDto, question))
                    .toList();
        } else {
            selectedOptions = null;
        }

        return new Answer(
                answerDto.getId(),
                submission,
                question,
                answerDto.getText(),
                selectedOptions
        );
    }
}
