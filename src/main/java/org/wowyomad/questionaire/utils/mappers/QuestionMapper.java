package org.wowyomad.questionaire.utils.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.wowyomad.questionaire.dto.OptionDTO;
import org.wowyomad.questionaire.dto.QuestionDto;
import org.wowyomad.questionaire.model.Option;
import org.wowyomad.questionaire.model.Question;
import org.wowyomad.questionaire.utils.enums.QuestionType;

import java.util.List;

@Component
@AllArgsConstructor
public class QuestionMapper {


    private final OptionMapper optionMapper;

    public QuestionDto mapToDto(Question question) {
        List<OptionDTO> options;
        if (question.getOptions() != null) {
            options = question.getOptions().stream().map(optionMapper::mapToDto).toList();
        } else {
            options = null;
        }

        return new QuestionDto(
                question.getId(),
                question.getType().toString(),
                question.getLabel(),
                question.getText(),
                question.isRequired(),
                question.isActive(),
                options
        );
    }

    public Question mapToEntity(QuestionDto questionDto) {
        Question question = new Question(
               questionDto.getId(),
                questionDto.getLabel(),
                questionDto.getText(),
                QuestionType.valueOf(questionDto.getType()),
                questionDto.isRequired(),
                questionDto.isActive(),
                null
        );

        List<Option> options;
        if (questionDto.getOptions() != null) {
            options = questionDto.getOptions().stream().map(option -> optionMapper.mapToEntity(option, question)).toList();
        } else {
            options = null;
        }

        question.setOptions(options);
        return question;
    }
}
