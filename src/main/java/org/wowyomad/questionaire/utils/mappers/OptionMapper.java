package org.wowyomad.questionaire.utils.mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.wowyomad.questionaire.dto.OptionDTO;
import org.wowyomad.questionaire.model.Option;
import org.wowyomad.questionaire.model.Question;

@Component
@AllArgsConstructor
public class OptionMapper {

    public OptionDTO mapToDto(Option option) {
        return new OptionDTO(
                option.getId(),
                option.getIndex(),
                option.getText(),
                option.getQuestion().getId()
        );
    }

    public Option mapToEntity(OptionDTO optionDto, Question question) {

        return new Option (
                optionDto.getId(),
                optionDto.getIndex(),
                optionDto.getText(),
                question
        );
    }
}
