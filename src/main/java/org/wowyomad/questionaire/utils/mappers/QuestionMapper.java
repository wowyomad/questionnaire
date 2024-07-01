package org.wowyomad.questionaire.utils.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.wowyomad.questionaire.dto.OptionDto;
import org.wowyomad.questionaire.dto.QuestionDto;
import org.wowyomad.questionaire.model.Option;
import org.wowyomad.questionaire.model.Question;
import org.wowyomad.questionaire.utils.enums.QuestionType;
import org.wowyomad.questionaire.utils.exceptions.OptionInvalidException;
import org.wowyomad.questionaire.utils.exceptions.QuestionInvalidException;

import java.util.Comparator;
import java.util.List;

@Component
@AllArgsConstructor
public class QuestionMapper {

    private final OptionMapper optionMapper;

    public QuestionDto mapToDto(Question question) {
        List<OptionDto> options;
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

    public Question mapToEntity(QuestionDto questionDto) throws QuestionInvalidException {
        try {
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
                boolean allIndexesNull = questionDto.getOptions().stream()
                        .allMatch(optionDto -> optionDto.getIndex() == null);

                if (allIndexesNull) {
                    options = questionDto.getOptions().stream()
                            .map(optionDto -> {
                                int index = questionDto.getOptions().indexOf(optionDto);
                                optionDto.setIndex(index);
                                return optionMapper.mapToEntity(optionDto, question);
                            })
                            .sorted(Comparator.comparing(Option::getIndex))
                            .toList();
                } else {
                    options = questionDto.getOptions().stream()
                            .peek(optionDto -> {
                                if (optionDto.getIndex() == null) {
                                    throw new OptionInvalidException("Option index cannot be null");
                                }
                            })
                            .map(optionDto -> optionMapper.mapToEntity(optionDto, question))
                            .sorted(Comparator.comparing(Option::getIndex))
                            .toList();

                    for (int i = 0; i < options.size(); i++) {
                        if (!options.get(i).getIndex().equals(i)) {
                            throw new OptionInvalidException("Option indexes must be sequential from 0 to " + (options.size() - 1));
                        }
                    }
                }
            } else {
                options = null;
            }

            question.setOptions(options);
            return question;
        } catch (IllegalArgumentException e) {
            throw new QuestionInvalidException("Invalid question or option: " + e.getMessage());
        }
    }
}
